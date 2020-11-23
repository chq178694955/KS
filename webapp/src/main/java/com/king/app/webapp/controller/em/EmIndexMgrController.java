package com.king.app.webapp.controller.em;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.em.entity.EmIndexCategory;
import com.king.em.entity.EmIndexTemplate;
import com.king.em.service.IEmIndexCategoryService;
import com.king.em.service.IEmIndexTemplateService;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
@Controller
@RequestMapping("em/indexMgr")
public class EmIndexMgrController extends BaseController {

    @Autowired
    private IEmIndexCategoryService emIndexCategoryService;
    @Autowired
    private IEmIndexTemplateService emIndexTemplateService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("em/indexMgr/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("findCategory")
    public Object findCategory(HttpServletRequest request){
        PageInfo<EmIndexCategory> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("searchKey",super.getParam("searchKey"));
        PageInfo<EmIndexCategory> pageInfo = emIndexCategoryService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @ResponseBody
    @RequestMapping("listCategory")
    public Object listCategory(HttpServletRequest request){
        List<EmIndexCategory> list = emIndexCategoryService.findAll();
        return list;
    }

    @RequestMapping("toAddCategory")
    public ModelAndView toAddCategory(){
        ModelAndView mv = new ModelAndView("em/indexMgr/addCategory");
        return mv;
    }

    @RequestMapping("addCategory")
    @ResponseBody
    public Object addCategory(HttpServletRequest request, EmIndexCategory category){
        EmIndexCategory category1 = emIndexCategoryService.findByName(category.getName());
        if(category1 != null){
            return new ResultResp("指标分类已存在");
        }else{
            if(emIndexCategoryService.add(category)){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @RequestMapping("toUpdateCategory")
    public ModelAndView toUpdateCategory(HttpServletRequest request,Integer id){
        ModelAndView mv = new ModelAndView("em/indexMgr/updateCategory");
        EmIndexCategory category = emIndexCategoryService.findById(id);
        mv.addObject("category",category);
        return mv;
    }

    @RequestMapping("updateCategory")
    @ResponseBody
    public Object update(HttpServletRequest request,EmIndexCategory category){
        if(emIndexCategoryService.update(category)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("deleteCategory")
    @ResponseBody
    public Object deleteCategory(HttpServletRequest request,Integer id){
        if(emIndexCategoryService.del(id)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @ResponseBody
    @RequestMapping("findTemplate")
    public Object findTemplate(HttpServletRequest request){
        PageInfo<EmIndexTemplate> page = super.getPage(request);
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(super.getParam("categoryId"))){
            criteria.put("categoryId",super.getParam("categoryId"));
        }
        PageInfo<EmIndexTemplate> pageInfo = emIndexTemplateService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAddTemplate")
    public ModelAndView toAddTemplate(){
        ModelAndView mv = new ModelAndView("em/indexMgr/addTemplate");
        return mv;
    }

    @RequestMapping("addTemplate")
    @ResponseBody
    public Object addTemplate(HttpServletRequest request, EmIndexTemplate template){
        EmIndexTemplate template1 = emIndexTemplateService.findByName(template.getName());
        if(template1 != null){
            return new ResultResp("指标已存在");
        }else{
            if(emIndexTemplateService.add(template)){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @RequestMapping("toUpdateTemplate")
    public ModelAndView toUpdateTemplate(HttpServletRequest request,EmIndexTemplate template){
        ModelAndView mv = new ModelAndView("em/indexMgr/updateTemplate");
        mv.addObject("template",template);
        return mv;
    }

    @RequestMapping("updateTemplate")
    @ResponseBody
    public Object updateTemplate(HttpServletRequest request,EmIndexTemplate template){
        if(emIndexTemplateService.update(template)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("deleteTemplate")
    @ResponseBody
    public Object deleteTemplate(HttpServletRequest request,Long id){
        if(emIndexTemplateService.del(id)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
