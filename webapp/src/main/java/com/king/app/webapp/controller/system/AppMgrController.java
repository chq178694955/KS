package com.king.app.webapp.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @创建人 chq
 * @创建时间 2020/5/4
 * @描述
 */
@Controller
@RequestMapping("sys/app")
public class AppMgrController {

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/app/main");
        return mv;
    }

}
