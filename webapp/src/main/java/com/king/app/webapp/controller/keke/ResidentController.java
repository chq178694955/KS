package com.king.app.webapp.controller.keke;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 chq
 * @创建时间 2020/4/20
 * @描述
 */
@Controller
@RequestMapping("keke/resident")
public class ResidentController extends BaseController {

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("keke/resident/main");
        return mv;
    }

    @RequestMapping("find")
    @ResponseBody
    public Object find(HttpServletRequest request){
        PageInfo page = getPage(request);
        return this.getGridData(page);
    }

}
