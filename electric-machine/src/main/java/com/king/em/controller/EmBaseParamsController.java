package com.king.em.controller;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmBaseParams;
import com.king.em.service.IEmBaseParamsService;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
@Controller
@RequestMapping("em/baseParams")
public class EmBaseParamsController extends BaseController {

    @Autowired
    private IEmBaseParamsService emBaseParamsService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("em/baseParams/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<EmBaseParams> page = super.getPage(request);
        Criteria criteria = new Criteria();
        PageInfo<EmBaseParams> pageInfo = emBaseParamsService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("em/baseParams/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, EmBaseParams params){
        params.setIsDefault(0);//用户自定义，非默认
        if(emBaseParamsService.add(params) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,Integer id){
        ModelAndView mv = new ModelAndView("em/baseParams/update");
        EmBaseParams params = emBaseParamsService.findById(id);
        mv.addObject("params",params);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,EmBaseParams params){
        if(emBaseParamsService.update(params) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Integer id){
        if(emBaseParamsService.delete(id) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
