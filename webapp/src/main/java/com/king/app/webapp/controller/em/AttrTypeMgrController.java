package com.king.app.webapp.controller.em;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.em.entity.EmAttrType;
import com.king.em.service.IEmAttrTypeService;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 属性类型
 * @创建人 chq
 * @创建时间 2020/11/20
 * @描述
 */
@Controller
@RequestMapping("em/attr/type")
public class AttrTypeMgrController extends BaseController {

    @Autowired
    private IEmAttrTypeService emAttrTypeService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("em/product/type/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<EmAttrType> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("searchKey",super.getParam("searchKey"));
        PageInfo<EmAttrType> pageInfo = emAttrTypeService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("em/product/type/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request, EmAttrType type){
        EmAttrType oldType = emAttrTypeService.findByName(type.getText());
        if(oldType != null){
            return new ResultResp(I18nUtils.get("em.attr.type.tip.exists",type.getText()));
        }else{
            if(emAttrTypeService.add(type) > 0){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,EmAttrType type){
        ModelAndView mv = new ModelAndView("em/product/type/update");
        mv.addObject("type",type);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,EmAttrType type){
        if(emAttrTypeService.update(type) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long id){
        if(emAttrTypeService.delete(id) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
