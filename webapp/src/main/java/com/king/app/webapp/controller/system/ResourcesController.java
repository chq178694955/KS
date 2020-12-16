package com.king.app.webapp.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.framework.base.BaseController;
import com.king.framework.model.ResultResp;
import com.king.system.entity.SysResources;
import com.king.system.service.ISysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/18
 * @描述
 */
@Controller
@RequestMapping("sys/resource")
public class ResourcesController extends BaseController {

    @Autowired
    private ISysResourcesService sysResourcesService;

    @RequestMapping("toMain")
    public ModelAndView toMai(){
        ModelAndView mv = new ModelAndView("sys/res/main");
        return mv;
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(){
        List<SysResources> resources = sysResourcesService.findAll();
        return convert2Tree(resources);
    }

    private JSONArray convert2Tree(List<SysResources> resources){
        JSONArray tree = new JSONArray();
        //默认pid=-1为顶级节点
        for(SysResources res :resources){
            if(res.getPid() == -1L){
                JSONObject node = convertNode(res);
                JSONArray children = getChildren(resources, res);
                node.put("children",children);
                tree.add(node);
            }
        }
        return tree;
    }

    private JSONObject convertNode(SysResources res){
        JSONObject node = new JSONObject();
        node.put("id",res.getId());
        node.put("title",res.getName());
        node.put("'field","name");
        node.put("spread",true);//全部展开
        return node;
    }

    private JSONArray getChildren(List<SysResources> resources,SysResources parent){
        JSONArray children = new JSONArray();
        for(SysResources res : resources){
            if(res.getPid() == parent.getId()){
                JSONObject node = convertNode(res);
                JSONArray childs = getChildren(resources, res);
                node.put("children",childs);
                children.add(node);
            }
        }
        return children;
    }

    @RequestMapping("owner/{roleId}")
    @ResponseBody
    public Object ownerResources(@PathVariable("roleId")Long roleId){
        List<SysResources> resources = sysResourcesService.getResourceByRoleId(roleId);
        return resources;
    }

    @RequestMapping("get/{resId}")
    @ResponseBody
    public Object getById(@PathVariable("resId")Long resId){
        return sysResourcesService.findById(resId);
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(SysResources res){
        int result = 0;
        if(res.getId() != null){
            result = sysResourcesService.updateResource(res);
        }else{
            result = sysResourcesService.addResource(res);
        }
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("del/{resId}")
    @ResponseBody
    public Object delete(@PathVariable("resId")Long resId){
        int result = sysResourcesService.delResource(resId);
        if(result > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
