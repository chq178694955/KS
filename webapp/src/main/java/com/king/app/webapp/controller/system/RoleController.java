package com.king.app.webapp.controller.system;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.I18nUtils;
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

    @RequestMapping("save")
    @ResponseBody
    public Object save(SysRole role){
        SysRole oldRole = sysRoleService.findByName(role.getName());
        int result = 0;
        if(role.getId() != null){
            if(oldRole != null){
                if(!role.getId().equals(oldRole.getId())){
                    return new ResultResp(I18nUtils.get("sys.role.tip.existsName"));
                }
            }
            result = sysRoleService.updateRole(role);
        }else{
            if(oldRole != null){
                return new ResultResp(I18nUtils.get("sys.role.tip.existsName"));
            }else{
                result = sysRoleService.addRole(role);
            }
        }
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long roleId){
        int result = sysRoleService.delRole(roleId);
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("authorization")
    @ResponseBody
    public Object authorization(Long roleId,String ids){
        int result = sysRoleService.authorization(roleId,ids);
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
