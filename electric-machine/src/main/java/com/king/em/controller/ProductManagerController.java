package com.king.em.controller;

import com.github.pagehelper.PageInfo;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.EmProduct;
import com.king.em.entity.Experiment;
import com.king.em.factory.ExperimentDataServiceFactory;
import com.king.em.service.IEmProductService;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建人 chq
 * @创建时间 2020/11/20
 * @描述
 */
@Controller
@RequestMapping("em/product")
public class ProductManagerController extends BaseController {

    @Autowired
    private IEmProductService emProductService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("em/product/main");
        return mv;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<EmProduct> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("searchKey",super.getParam("searchKey"));
        criteria.put("userId", AuthUtils.getUserInfo().getId());
        PageInfo<EmProduct> pageInfo = emProductService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @ResponseBody
    @RequestMapping("data/find1")
    public Object findData1(HttpServletRequest request,Integer productId){
        PageInfo<Experiment> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        PageInfo<Experiment> pageInfo =  ExperimentDataServiceFactory.getInstance().getService(ExperimentType.STEP).find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }
    @ResponseBody
    @RequestMapping("data/find2")
    public Object findData2(HttpServletRequest request,Integer productId){
        PageInfo<Experiment> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        PageInfo<Experiment> pageInfo =  ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SIN).find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }
    @ResponseBody
    @RequestMapping("data/find3")
    public Object findData3(HttpServletRequest request,Integer productId){
        PageInfo<Experiment> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        PageInfo<Experiment> pageInfo =  ExperimentDataServiceFactory.getInstance().getService(ExperimentType.OVERLOAD).find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }
    @ResponseBody
    @RequestMapping("data/find4")
    public Object findData4(HttpServletRequest request,Integer productId){
        PageInfo<Experiment> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        PageInfo<Experiment> pageInfo =  ExperimentDataServiceFactory.getInstance().getService(ExperimentType.EMPTYLOAD).find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }
    @ResponseBody
    @RequestMapping("data/find5")
    public Object findData5(HttpServletRequest request,Integer productId){
        PageInfo<Experiment> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        PageInfo<Experiment> pageInfo =  ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SPEED).find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }
    @ResponseBody
    @RequestMapping("data/find6")
    public Object findData6(HttpServletRequest request,Integer productId){
        PageInfo<Experiment> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        PageInfo<Experiment> pageInfo =  ExperimentDataServiceFactory.getInstance().getService(ExperimentType.CONSTANTLOAD).find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

}
