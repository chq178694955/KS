package com.king.em.controller;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmIndexCategory;
import com.king.em.entity.EmIndexDetail;
import com.king.em.entity.EmIndexGroup;
import com.king.em.entity.EmIndexTemplate;
import com.king.em.service.IEmIndexCategoryService;
import com.king.em.service.IEmIndexDetailService;
import com.king.em.service.IEmIndexGroupService;
import com.king.em.service.IEmIndexTemplateService;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.framework.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
@Controller
@RequestMapping("em/myIndex")
public class EmMyIndexController extends BaseController {

    @Autowired
    private IEmIndexCategoryService emIndexCategoryService;
    @Autowired
    private IEmIndexTemplateService emIndexTemplateService;
    @Autowired
    private IEmIndexGroupService emIndexGroupService;
    @Autowired
    private IEmIndexDetailService emIndexDetailService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("em/myIndex/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<EmIndexDetail> page = super.getPage(request);
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(super.getParam("groupId"))){
            criteria.put("groupId",super.getParam("groupId"));
        }

        criteria.put("userId", AuthUtils.getUserInfo().getId());//带入条件，只查询属于自己创建的指标
        PageInfo<EmIndexDetail> pageInfo = emIndexDetailService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("em/myIndex/add");
        List<EmIndexCategory> categories = emIndexCategoryService.findAll();
        List<EmIndexTemplate> templates = emIndexTemplateService.findAll();
        mv.addObject("categories",categories);
        mv.addObject("templates",templates);
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request){
        // 1.获取用户指标分组并入库（每一个用户下的组名必须保证唯一，重复给出提示）
        String groupName = super.getParam("groupName");
        Criteria criteriaGroup = new Criteria();
        criteriaGroup.put("groupName",groupName);
        criteriaGroup.put("userId",AuthUtils.getUserInfo().getId());
        EmIndexGroup group = emIndexGroupService.findByName(criteriaGroup);
        if(group != null){
            return new ResultResp("用户自定义指标组名重复");
        }
        group = new EmIndexGroup();
        group.setName(groupName);
        group.setCreateTime(new Date());
        group.setCreatorId(AuthUtils.getUserInfo().getId());
        boolean flagGroup = emIndexGroupService.add(group);

        if(flagGroup){
            // 2.通过系统预设指标找到form表单请求过来对应的数据
            List<EmIndexTemplate> templates = emIndexTemplateService.findAll();
            if(!CollectionUtils.isEmpty(templates)){
                List<EmIndexDetail> details = new ArrayList<>();
                for(EmIndexTemplate template : templates){
                    EmIndexDetail detail = new EmIndexDetail();
                    detail.setIndexId(template.getId());
                    detail.setGroupId(group.getId());
                    detail.setVal(new BigDecimal(super.getDoubleParam("val_" + template.getId())));
                    detail.setWeight(new BigDecimal(super.getDoubleParam("weight_" + template.getId())));
                    details.add(detail);
                }
                boolean flagDetails = emIndexDetailService.batchAdd(details);
                if(flagDetails){
                    return ResultResp.ok();
                }else{
                    return ResultResp.fail();
                }
            }else{
                return new ResultResp("请填写完整的指标数据");
            }
        }else{
            // 2.创建分组失败
            return new ResultResp("创建指标分组失败");
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,Long groupId){
        ModelAndView mv = new ModelAndView("em/myIndex/update");
        List<EmIndexCategory> categories = emIndexCategoryService.findAll();
        List<EmIndexTemplate> templates = emIndexTemplateService.findAll();
        mv.addObject("categories",categories);
        mv.addObject("templates",templates);
        //获取分组信息
        EmIndexGroup group = emIndexGroupService.findById(groupId);
        mv.addObject("group",group);
        //根据分组ID获取该用户自定义的指标项
        List<EmIndexDetail> details = emIndexDetailService.findByGroupId(groupId);
        mv.addObject("details",details);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request){
        // 1.获取用户指标分组并入库（每一个用户下的组名必须保证唯一，重复给出提示）
        String groupName = super.getParam("groupName");
        Long groupId = super.getLongParam("groupId");
        EmIndexGroup oldGroup = emIndexGroupService.findById(groupId);
        Criteria criteriaGroup = new Criteria();
        criteriaGroup.put("groupName",groupName);
        criteriaGroup.put("userId",AuthUtils.getUserInfo().getId());
        EmIndexGroup group = emIndexGroupService.findByName(criteriaGroup);
        if(group != null && oldGroup != null && group.getId() != oldGroup.getId()){
            return new ResultResp("用户自定义指标组名重复");
        }
        group = new EmIndexGroup();
        group.setId(groupId);
        group.setName(groupName);
        group.setCreateTime(new Date());
        group.setCreatorId(AuthUtils.getUserInfo().getId());
        boolean flagGroup = emIndexGroupService.update(group);

        if(flagGroup){
            // 2.通过系统预设指标找到form表单请求过来对应的数据
            List<EmIndexTemplate> templates = emIndexTemplateService.findAll();
            if(!CollectionUtils.isEmpty(templates)){
                List<EmIndexDetail> details = new ArrayList<>();
                for(EmIndexTemplate template : templates){
                    EmIndexDetail detail = new EmIndexDetail();
                    detail.setId(super.getLongParam("id_" + template.getId()));
                    detail.setIndexId(template.getId());
                    detail.setGroupId(group.getId());
                    detail.setVal(new BigDecimal(super.getDoubleParam("val_" + template.getId())));
                    detail.setWeight(new BigDecimal(super.getDoubleParam("weight_" + template.getId())));
                    details.add(detail);
                }
                // 3.批量更新明细
                boolean flagDetails = emIndexDetailService.batchUpdate(details);
                if(flagDetails){
                    return ResultResp.ok();
                }else{
                    return ResultResp.fail();
                }
            }else{
                return new ResultResp("请填写完整的指标数据");
            }
        }else{
            // 2.创建分组失败
            return new ResultResp("创建指标分组失败");
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long groupId){
        if(emIndexDetailService.delByGroupId(groupId)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("groupList")
    @ResponseBody
    public Object groupList(HttpServletRequest request){
        return emIndexGroupService.findAll();

    }

}
