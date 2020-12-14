package com.king.app.webapp.controller.system;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.I18nUtils;
import com.king.system.entity.SysUser;
import com.king.system.service.ISysUserService;
import com.king.system.vo.SysUserVO;
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
        PageInfo<SysUserVO> pageInfo = sysUserService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("sys/user/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, SysUserVO user){
        SysUser oldUser = sysUserService.findByUserName(user.getUsername());
        if(oldUser != null){
            return new ResultResp(I18nUtils.get("sys.user.tip.existsUser",user.getUsername()));
        }else{
            if(sysUserService.addUser(user) > 0){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,SysUserVO user){
        ModelAndView mv = new ModelAndView("sys/user/update");
        mv.addObject("user",user);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,SysUserVO user){
        if(sysUserService.updateUser(user) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long userId){
        if(sysUserService.delUser(userId) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
