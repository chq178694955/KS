package com.king.game.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.framework.utils.I18nUtils;
import com.king.game.entity.VoteItemGroup;
import com.king.game.service.IVoteItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Controller
@RequestMapping("game/voteItemGroup")
public class VoteItemGroupController extends BaseController {

    @Autowired
    private IVoteItemGroupService voteItemGroupService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("game/itemGroup/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<VoteItemGroup> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("name",super.getParam("name"));
        PageInfo<VoteItemGroup> pageInfo = voteItemGroupService.findPage(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("game/itemGroup/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, VoteItemGroup group){
        if(voteItemGroupService.addGroup(group) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,VoteItemGroup group){
        ModelAndView mv = new ModelAndView("game/itemGroup/update");
        mv.addObject("group",group);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,VoteItemGroup group){
        if(voteItemGroupService.updateGroup(group) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long id){
        if(voteItemGroupService.delGroup(id) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public List<VoteItemGroup> list(HttpServletRequest request){
        String flag = super.getParam("flag");
        List<VoteItemGroup> datas = voteItemGroupService.selectAll();
        if(datas == null)datas = new ArrayList<>();
        if(StringUtil.isNotEmpty(flag) && flag.equals("1")){
            VoteItemGroup group = new VoteItemGroup();
            group.setId(0L);
            group.setName(I18nUtils.get("com.select.all"));
            datas.add(0,group);
        }
        return datas;
    }
}
