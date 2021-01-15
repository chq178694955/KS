package com.king.keke.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.keke.entity.VoteInfo;
import com.king.keke.service.IVoteService;
import com.king.keke.statis.VoteContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("keke/voteStatis")
public class VoteStatisController extends BaseController {

    @Autowired
    private IVoteService voteService;
    @Autowired
    private VoteContext voteContext;

    @RequestMapping("toMain")
    public ModelAndView toMain(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("keke/residentVote/statis");
        Criteria criteria = new Criteria();
        PageInfo<VoteInfo> page = super.getPage(request);
        PageInfo<VoteInfo> pageInfo = voteService.findInfo(page,criteria,true);
        mv.addObject("voteInfoList",pageInfo.getList());
        return mv;
    }

    @RequestMapping("statis")
    @ResponseBody
    public Object statis(HttpServletRequest request){
        Long voteId = super.getLongParam("voteId");
        Integer ruleId = super.getIntegerParam("ruleId");

        //执行投票
        JSONObject result = voteContext.getStrategy(ruleId).vote(voteId);
        return result;
    }


}
