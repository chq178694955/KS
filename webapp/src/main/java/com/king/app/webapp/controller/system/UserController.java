package com.king.app.webapp.controller.system;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.system.entity.SysUser;
import com.king.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 chq
 * @创建时间 2020/4/12
 * @描述
 */
@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/user/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<SysUser> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("state",super.getIntegerParam("state"));
        criteria.put("searchKey",super.getParam("searchKey"));
        PageInfo<SysUser> pageInfo = sysUserService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

}
