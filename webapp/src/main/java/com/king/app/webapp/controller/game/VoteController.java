package com.king.app.webapp.controller.game;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.DateUtils;
import com.king.framework.utils.I18nUtils;
import com.king.game.entity.*;
import com.king.game.service.*;
import com.king.game.vo.VoteItemVO;
import com.king.game.vo.VoteVO;
import com.king.system.po.UserInfo;
import com.king.system.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 投票管理
 * @创建人 chq
 * @创建时间 2020/4/15
 * @描述
 */
@Controller
@RequestMapping("game/vote")
public class VoteController extends BaseController {

    @Autowired
    private IVoteService voteService;
    @Autowired
    private IVoteTemplateService voteTemplateService;
    @Autowired
    private IVoteItemGroupService voteItemGroupService;
    @Autowired
    private IVoteItemService voteItemService;
    @Autowired
    private IVoteRecordService voteRecordService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("game/vote/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<Vote> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("searchKey",super.getParam("searchKey"));
        String timeRange = super.getParam("timeRange");
        if(StringUtil.isNotEmpty(timeRange)){
            String[] strs = timeRange.split("~");
            criteria.put("startTime", DateUtils.parse(strs[0],"yyyy-MM-dd"));
            criteria.put("endTime",DateUtils.parse(strs[1],"yyyy-MM-dd"));
        }

        criteria.put("status",super.getIntegerParam("searchKey"));
        PageInfo<VoteVO> pageInfo = voteService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("game/vote/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request){
        String voteInfo = super.getParam("voteInfo");
        JSONObject json = JSONObject.parseObject(voteInfo);
        JSONArray groups = json.getJSONArray("groups");
        if(groups.isEmpty()){
            return new ResultResp(I18nUtils.get("vote.itemGroup.tip.nameEmpty"));
        }
        String[] times = json.getString("timeRange").replaceAll(" ","").split("~");
        Date startTime = DateUtils.parse(times[0],DateUtils.YMD);
        Date endTime = DateUtils.parse(times[1],DateUtils.YMD);
        Vote vote = JSONObject.parseObject(voteInfo,Vote.class);
        vote.setStartTime(startTime);
        vote.setEndTime(endTime);
        vote.setCreateTime(new Date());
        UserInfo userInfo = AuthUtils.getUserInfo();
        vote.setCreateId(userInfo.getId());

        int result = voteService.addVote(vote,groups);
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long id){
        if(voteService.delVote(id) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("validVote")
    @ResponseBody
    public Object validVote(HttpServletRequest request,Long voteId){
        Vote vote = voteService.getVote(voteId);
        boolean flag = DateUtils.compareDate(vote.getEndTime(),new Date());
        if(!flag){
            return new ResultResp(I18nUtils.get("vote.tip.finishVote"));
        }
        VoteTemplate template = voteTemplateService.getTemplate(vote.getTemplateId());
        List<VoteItemGroup> groups = voteItemGroupService.getGroupsByTemplate(template.getId());
        if(groups == null || groups.size() == 0)return new ResultResp(I18nUtils.get("vote.tip.dataIncomplet"));
        List<Long> groupIds = new ArrayList<>();
        for(VoteItemGroup g : groups){
            groupIds.add(g.getId());
        }
        List<VoteItemVO> items = voteItemService.getItemByGroupIds(groupIds);
        if(items == null || items.size() == 0)return new ResultResp(I18nUtils.get("vote.tip.dataIncomplet"));
        return ResultResp.ok();
    }

    @RequestMapping("voting")
    public ModelAndView toVoting(HttpServletRequest request,Long voteId){
        ModelAndView mv = new ModelAndView("game/vote/voting");
        Long userId = AuthUtils.getUserInfo().getId();

        //获取投票明细
        Vote vote = voteService.getVote(voteId);
        mv.addObject("vote",vote);
        VoteTemplate template = voteTemplateService.getTemplate(vote.getTemplateId());
        List<VoteItemGroup> groups = voteItemGroupService.getGroupsByTemplate(template.getId());
        mv.addObject("groups",groups);
        List<Long> groupIds = new ArrayList<>();
        for(VoteItemGroup g : groups){
            groupIds.add(g.getId());
        }
        List<VoteItemVO> items = voteItemService.getItemByGroupIds(groupIds);

        //如果已投票，获取已投信息
        VoteRecord record = voteRecordService.getVoteRecord(userId, voteId);
        if(record != null){
            mv.addObject("record",record);
            List<VoteRecordDetail> details = voteRecordService.findRecordDetails(record.getId());
            if(details != null){
                //校验item是否被选中，0-未选中 1-选中
                for(VoteRecordDetail detail : details){
                    Map<String,String> idMap = new HashMap<>();
                    String[] strs = detail.getItemIds().split(",");
                    for(String s : strs){
                        idMap.put(s,"");
                    }
                    for(VoteItemVO vo : items){
                        if(detail.getGroupId() == vo.getGroupId()){
                            if(idMap.containsKey(vo.getValue())){
                                vo.setState(1);
                            }else{
                                vo.setState(0);
                            }
                        }
                    }
                }
            }
        }
        mv.addObject("items",items);
        return mv;
    }

    @RequestMapping("doVoting")
    @ResponseBody
    public Object doVoting(HttpServletRequest request){
        String voteInfo = super.getParam("voteInfo");
        JSONObject vote = JSONObject.parseObject(voteInfo);
        Long voteId = vote.getLongValue("voteId");
        JSONArray groups = vote.getJSONArray("groups");
        Long userId = AuthUtils.getUserInfo().getId();
        int result = voteRecordService.addVoteRecord(userId, voteId, groups);
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
