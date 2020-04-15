package com.king.app.webapp.controller.game;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.I18nUtils;
import com.king.game.entity.VoteTemplate;
import com.king.game.service.IVoteTemplateService;
import com.king.system.entity.SysDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Controller
@RequestMapping("game/voteTemplate")
public class VoteTemplateController extends BaseController {

    @Autowired
    private IVoteTemplateService voteTemplateService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("game/template/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<VoteTemplate> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("name",super.getParam("name"));
        PageInfo<VoteTemplate> pageInfo = voteTemplateService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("game/template/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, VoteTemplate template){
        if(voteTemplateService.addVoteTemplate(template) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,VoteTemplate template){
        ModelAndView mv = new ModelAndView("game/template/update");
        mv.addObject("template",template);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,VoteTemplate template){
        if(voteTemplateService.updateVoteTemplate(template) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long id){
        if(voteTemplateService.deleteVoteTemplate(id) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }
}
