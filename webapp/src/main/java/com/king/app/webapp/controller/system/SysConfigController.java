package com.king.app.webapp.controller.system;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.framework.utils.I18nUtils;
import com.king.system.entity.SysConfig;
import com.king.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统配置
 * @创建人 chq
 * @创建时间 2020/4/22
 * @描述
 */
@Controller
@RequestMapping("sys/config")
public class SysConfigController extends BaseController {

    @Autowired
    private ISysConfigService sysConfigService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/conf/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<SysConfig> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("searchKey",super.getParam("searchKey"));
        PageInfo<SysConfig> pageInfo = sysConfigService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("sys/conf/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, SysConfig conf){
        SysConfig oldConf = sysConfigService.findByKey(conf.getText());
        if(oldConf != null){
            return new ResultResp(I18nUtils.get("sys.user.tip.existsUser",conf.getText()));
        }else{
            conf.setUseFlag(1);
            if(sysConfigService.addConfig(conf) > 0){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,SysConfig conf){
        ModelAndView mv = new ModelAndView("sys/conf/update");
        mv.addObject("conf",conf);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,SysConfig conf){
        if(sysConfigService.updateConfig(conf) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,String text){
        if(sysConfigService.delConfig(text) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
