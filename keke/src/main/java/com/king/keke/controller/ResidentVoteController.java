package com.king.keke.controller;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.keke.entity.ResidentInfo;
import com.king.keke.entity.VoteInfo;
import com.king.keke.service.IResidentInfoService;
import com.king.keke.service.IResidentVoteService;
import com.king.keke.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

}
