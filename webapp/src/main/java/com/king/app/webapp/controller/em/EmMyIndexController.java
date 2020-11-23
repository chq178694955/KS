package com.king.app.webapp.controller.em;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
@Controller
@RequestMapping("em/myIndex")
public class EmMyIndexController {

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("em/myIndex/main");
        return mv;
    }

}
