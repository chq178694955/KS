package com.king.app.webapp.controller.game;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItem;
import com.king.game.entity.VoteItemGroup;
import com.king.game.service.IVoteItemGroupService;
import com.king.game.service.IVoteItemService;
import com.king.game.vo.VoteItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Controller
@RequestMapping("game/voteItem")
public class VoteItemController extends BaseController {

    @Autowired
    private IVoteItemService voteItemService;

    @Autowired
    private IVoteItemGroupService voteItemGroupService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("game/item/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<VoteItem> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("groupId",super.getIntegerParam("groupId"));
        PageInfo<VoteItemVO> pageInfo = voteItemService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("game/item/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, VoteItem item){
        if(voteItemService.addItem(item) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,VoteItem item){
        ModelAndView mv = new ModelAndView("game/item/update");
        mv.addObject("item",item);
        List<VoteItemGroup> groups = voteItemGroupService.selectAll();
        mv.addObject("groups",groups);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,VoteItem item){
        if(voteItemService.updateItem(item) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long id){
        if(voteItemService.delItem(id) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }
}
