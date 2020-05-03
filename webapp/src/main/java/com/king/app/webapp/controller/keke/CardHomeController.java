package com.king.app.webapp.controller.keke;

import com.king.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @创建人 chq
 * @创建时间 2020/4/28
 * @描述
 */
@Controller
@RequestMapping("keke")
public class CardHomeController extends BaseController {

    @RequestMapping("carHome")
    public ModelAndView carHome(){
        ModelAndView mv = new ModelAndView("keke/car/home");
        return mv;
    }

}
