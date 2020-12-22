package com.king.keke.controller;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
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
import java.util.Date;
import java.util.List;

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
        PageInfo<VoteOption> result = new PageInfo<>();
        result.setList(voteService.findOption(voteId));
        return getGridData(result);
    }

    @RequestMapping("findOptionItem")
    @ResponseBody
    public Object findOptionItem(Long voteOptionId){
        PageInfo<VoteOptionItem> result = new PageInfo<>();
        result.setList(voteService.findOptionItem(voteOptionId));
        return getGridData(result);
    }

    @RequestMapping("toAddVote")
    public ModelAndView toAddVote(){
        ModelAndView mv = new ModelAndView("keke/vote/add");
        return mv;
    }

    @ResponseBody
    @RequestMapping("add")
    public Object addVote(String title){
        if(voteService.getByTitle(title) != null){
            return new ResultResp<>("该投票内容已存在");
        }
        VoteInfo vote = new VoteInfo();
        vote.setTitle(title);
        vote.setCreateTime(new Date());
        if(voteService.addVote(vote)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object addDelete(Long voteId){
        List<VoteOption> options = voteService.findOption(voteId);
        if(options != null && options.size() > 0){
            return new ResultResp<>("请删除投票议题");
        }else{
            if(voteService.delVote(voteId)){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @ResponseBody
    @RequestMapping("addOption")
    public Object addOption(Long voteId,String optionName,Short optionType){
        VoteOption option = voteService.getOption(voteId,optionName);
        if(option != null){
            return new ResultResp<>("请勿重复添加");
        }
        VoteOption newOption = new VoteOption();
        newOption.setVoteId(voteId);
        newOption.setName(optionName);
        newOption.setType(optionType);
        if(voteService.addVoteOption(newOption)){
            List<VoteOption> options = voteService.findOption(voteId);
            ResultResp<List<VoteOption>> result = new ResultResp<>(0,"添加成功");
            result.setData(options);
            return result;
        }else{
            return ResultResp.fail();
        }
    }

    @ResponseBody
    @RequestMapping("deleteOption")
    public Object addDeleteOption(Long id){
        List<VoteOptionItem> items = voteService.findOptionItem(id);
        if(items != null && items.size() > 0){
            return new ResultResp<>("请删除议题的选项");
        }else{
            if(voteService.delVoteOption(id)){
                List<VoteOption> options = voteService.findOption(id);
                ResultResp<List<VoteOption>> result = new ResultResp<>(0,"删除成功");
                result.setData(options);
                return result;
            }else{
                return ResultResp.fail();
            }
        }
    }

    @ResponseBody
    @RequestMapping("addOptionItem")
    public Object addOptionItem(Long voteOptionId,String itemName){
        Criteria criteria = new Criteria();
        criteria.put("voteOptionId",voteOptionId);
        criteria.put("name",itemName);
        VoteOptionItem item = voteService.getOptionItem(criteria);
        if(item != null){
            return new ResultResp<>("请勿重复添加");
        }
        VoteOptionItem newItem = new VoteOptionItem();
        newItem.setVoteOptionId(voteOptionId);
        newItem.setName(itemName);
        if(voteService.addVoteOptionItem(newItem)){
            List<VoteOptionItem> options = voteService.findOptionItem(voteOptionId);
            ResultResp<List<VoteOptionItem>> result = new ResultResp<>(0,"添加成功");
            result.setData(options);
            return result;
        }else{
            return ResultResp.fail();
        }
    }

    @ResponseBody
    @RequestMapping("deleteItem")
    public Object deleteItem(Long id){
        Criteria criteria = new Criteria();
        criteria.put("id",id);
        VoteOptionItem item = voteService.getOptionItem(criteria);
        if(voteService.delVoteOptionItem(id)){
            List<VoteOptionItem> items = voteService.findOptionItem(item.getVoteOptionId());
            ResultResp<List<VoteOptionItem>> result = new ResultResp<>(0,"删除成功");
            result.setData(items);
            return result;
        }else{
            return ResultResp.fail();
        }
    }

}
