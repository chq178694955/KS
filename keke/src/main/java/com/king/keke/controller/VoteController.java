package com.king.keke.controller;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.keke.entity.VoteInfo;
import com.king.keke.entity.VoteOption;
import com.king.keke.entity.VoteOptionItem;
import com.king.keke.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("keke/vote")
public class VoteController extends BaseController {

    @Autowired
    private IVoteService voteService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("keke/vote/main");
        return mv;
    }

    @RequestMapping("findInfo")
    @ResponseBody
    public Object findInfo(HttpServletRequest request){
        PageInfo<VoteInfo> page = super.getPage(request);
        Criteria criteria = new Criteria();
        return super.getGridData(voteService.findInfo(page,criteria,isDownloadReq()));
    }

    @RequestMapping("findOption")
    @ResponseBody
    public Object findOption(Long voteId){
//        PageInfo<VoteOption> result = new PageInfo<>();
//        result.setList(voteService.findOption(voteId));
//        return getGridData(result);
        return voteService.findOption(voteId);
    }

    @RequestMapping("findOptionItem")
    @ResponseBody
    public Object findOptionItem(Long voteOptionId){
//        PageInfo<VoteOptionItem> result = new PageInfo<>();
//        result.setList(voteService.findOptionItem(voteOptionId));
//        return result;
        return voteService.findOptionItem(voteOptionId);
    }

}
