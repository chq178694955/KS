package com.king.keke.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.keke.entity.*;
import com.king.keke.service.IResidentInfoService;
import com.king.keke.service.IResidentVoteService;
import com.king.keke.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("keke/residentVote")
public class ResidentVoteController extends BaseController {

    @Autowired
    private IResidentInfoService residentInfoService;
    @Autowired
    private IVoteService voteService;
    @Autowired
    private IResidentVoteService residentVoteService;

    @RequestMapping("toMain")
    public ModelAndView toMain(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("keke/residentVote/main");
        List<ResidentInfo> residentInfoList = residentInfoService.findBuildings();
        mv.addObject("residentInfoList",residentInfoList);

        Criteria criteria = new Criteria();
        PageInfo<VoteInfo> page = super.getPage(request);
        PageInfo<VoteInfo> pageInfo = voteService.findInfo(page,criteria,true);
        mv.addObject("voteInfoList",pageInfo.getList());
        return mv;
    }

    @ResponseBody
    @RequestMapping("building")
    public Object residentBuilding(String building,Long voteId){
        return residentVoteService.findByBuilding(building,voteId);
    }

    @RequestMapping("toVote")
    public ModelAndView toVote(Long residentId,Long voteId){
        ModelAndView mv = new ModelAndView("keke/residentVote/vote");
        List<ResidentVote> votes = residentVoteService.findResidentVote(residentId,voteId);
        Map<String,String> votesMap = votes.stream().collect(Collectors.toMap(
                k -> k.getVoteId().toString() + k.getVoteOptionId().toString(),
                s -> s.getItemIds()
        ));

        ResidentInfo resident = residentInfoService.findById(residentId);
        VoteInfo vote = voteService.findVoteById(voteId);
        List<VoteOption> options = voteService.findOption(voteId);
        for(VoteOption o : options){
            List<VoteOptionItem> items = voteService.findOptionItem(o.getId());
            for(VoteOptionItem item : items){
                String itemIds = votesMap.get(vote.getId().toString() + o.getId().toString());
                if(StringUtils.isEmpty(itemIds)){
                    item.setChecked(false);
                }else{
                    String[] aryItemId = itemIds.split(",");
                    List<Long> ids = new ArrayList<>();
                    for(String s : aryItemId){
                        ids.add(Long.valueOf(s));
                    }
                    Map<Long,Long> idsMap = ids.stream().collect(Collectors.toMap(k->k,v->v));
                    if(idsMap.containsKey(item.getId())){
                        item.setChecked(true);
                    }else{
                        item.setChecked(false);
                    }
                }
            }
            o.setItems(items);
        }

        mv.addObject("resident",resident);
        mv.addObject("vote",vote);
        mv.addObject("options",options);
        //mv.addObject("votes",votes);
        return mv;
    }

    @ResponseBody
    @RequestMapping("vote")
    public Object vote(HttpServletRequest request){
        Long residentId = super.getLongParam("residentId");
        Long voteId = super.getLongParam("voteId");
        String options = super.getParam("options");
        JSONArray array = (JSONArray) JSONArray.parse(options);
        List<ResidentVote> datas = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            JSONObject obj = array.getJSONObject(i);
            String key = obj.getString("key");
            String value = obj.getString("value");
            String[] keys = key.split("_");
//            System.out.println("optionId:" + keys[1]);
//            System.out.println("values:" + value);
            ResidentVote vote = new ResidentVote();
            vote.setResidentId(residentId);
            vote.setVoteId(voteId);
            vote.setVoteOptionId(Long.valueOf(keys[1]));
            vote.setItemIds(value);
            datas.add(vote);
        }

        if(residentVoteService.vote(datas)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
