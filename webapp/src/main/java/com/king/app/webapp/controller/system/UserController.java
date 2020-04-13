package com.king.app.webapp.controller.system;

import com.king.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @创建人 chq
 * @创建时间 2020/4/12
 * @描述
 */
@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/user/main");
        return mv;
    }

}
