package com.king.app.webapp.controller.system;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.system.entity.SysRole;
import com.king.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/18
 * @描述
 */
@Controller
@RequestMapping("sys/role")
public class RoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/role/main");
        return mv;
    }

    @RequestMapping("list")
    @ResponseBody
    public List<SysRole> list(){
        return sysRoleService.findAll();
    }

    @RequestMapping("find")
    @ResponseBody
    public Object find(HttpServletRequest request){
        String searchKey = super.getParam("searchKey");
        PageInfo<SysRole> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("searchKey",searchKey);
        PageInfo<SysRole> pageInfo = sysRoleService.find(page,criteria,isDownloadReq());
        return super.getGridData(pageInfo);
    }

}
