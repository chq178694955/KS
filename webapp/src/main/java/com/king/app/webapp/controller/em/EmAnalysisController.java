package com.king.app.webapp.controller.em;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.*;
import com.king.em.factory.ExperimentDataServiceFactory;
import com.king.em.service.IEmBaseParamsService;
import com.king.em.service.IEmProductService;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.system.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 性能评估（分析）
 * @创建人 chq
 * @创建时间 2020/11/24
 * @描述
 */
@Controller
@RequestMapping("em/analysis")
public class EmAnalysisController extends BaseController {

    @Autowired
    private IEmProductService emProductService;
    @Autowired
    private IEmBaseParamsService emBaseParamsService;

    @RequestMapping("toMain")
    public ModelAndView toMain(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("em/analysis/main");
        Criteria criteria = new Criteria();
        criteria.put("userId", AuthUtils.getUserInfo().getId());
        List<EmProduct> productList = emProductService.findAll(criteria);
        mv.addObject("productList",productList);
        if(null != productList && productList.size() > 0){
            Integer productId = productList.get(0).getId();//获取第一个项目的实验数据，用于前端显示
            //获取实验数据
            Map<String,Object> experimentDataObj = this.getExperimentData(productId,request);
            mv.addObject("experimentDataObj",experimentDataObj);
        }
        return mv;
    }

    /**
     * 获取实验数据
     *
     * @param productId
     * @return
     */
    private Map<String,Object> getExperimentData(Integer productId,HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);

        List<Experiment> stepList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.STEP).findAll(criteria);
        List<Experiment> sinList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SIN).findAll(criteria);
        List<Experiment> overloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.OVERLOAD).findAll(criteria);
        List<Experiment> emptyloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.EMPTYLOAD).findAll(criteria);
        List<Experiment> speedList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SPEED).findAll(criteria);
        List<Experiment> constantloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.CONSTANTLOAD).findAll(criteria);
        result.put("stepList",dataToAry(ExperimentType.STEP,stepList));//取全部数据
        result.put("emptyloadList",dataToAry(ExperimentType.EMPTYLOAD,emptyloadList));//取全部数据
        //判断数据是否完整
        if(CollectionUtils.isEmpty(sinList) || CollectionUtils.isEmpty(sinList) || CollectionUtils.isEmpty(overloadList)
                || CollectionUtils.isEmpty(emptyloadList) || CollectionUtils.isEmpty(speedList) || CollectionUtils.isEmpty(constantloadList)){
            result.put("hasDataFlag",false);
        }else{
            result.put("hasDataFlag",true);
        }

        if(Boolean.valueOf(result.get("hasDataFlag").toString())){
            final List<Experiment> sinList2 = new ArrayList<>();
            sinList.stream().limit(10).forEach(k->{sinList2.add(k);});
            final List<Experiment> constantloadList2 = new ArrayList<>();
            constantloadList.stream().limit(100).forEach(k->{constantloadList2.add(k);});
            result.put("constantloadList",dataToAry(ExperimentType.CONSTANTLOAD,constantloadList2));//只需要取前100条
            result.put("sinList",dataToAry(ExperimentType.SIN,sinList2));//只需要取前10条

            result.put("speedEmpty",((EmDataOverload)overloadList.get(0)).getSpeedEmpty());//只需要取第1条 max
            result.put("speedFixedLoad",((EmDataOverload)overloadList.get(0)).getSpeedFixedLoad());//只需要取第1条 min

            result.put("speedMax",((EmDataSpeed)speedList.get(0)).getSpeedMax());//只需要取第1条 max
            result.put("speedMin",((EmDataSpeed)speedList.get(0)).getSpeedMin());//只需要取第1条 min

            result.put("speedSetter",((EmDataConstantload)constantloadList.get(0)).getSpeedSetter());
            result.put("torqueOverload",((EmDataConstantload)constantloadList.get(0)).getTorqueOverload());
        }else{
            result.put("msg","实验数据不完整，请重新导入");
        }

        return result;
    }

    /**
     * List 转换 JSONArray
     * @param type
     * @param datas
     * @return
     */
    private JSONArray dataToAry(ExperimentType type,List<Experiment> datas){
        JSONArray ary = new JSONArray();
        if(type == null)return ary;
        for(Experiment e : datas){
            if(ExperimentType.STEP == type){
                EmDataStep step = (EmDataStep)e;
                JSONObject obj = new JSONObject();
                obj.put("dataTime",step.getDataTime());
                obj.put("speed",step.getSpeed());
                ary.add(obj);
            }else if(ExperimentType.SIN == type){
                EmDataSin sin = (EmDataSin)e;
                JSONObject obj = new JSONObject();
                obj.put("speed10",sin.getSpeed10());
                ary.add(obj);
            }else if(ExperimentType.EMPTYLOAD == type){
                EmDataEmptyload emp = (EmDataEmptyload)e;
                JSONObject obj = new JSONObject();
                obj.put("speedForward",emp.getSpeedForward());
                obj.put("speedReverse",emp.getSpeedReverse());
                ary.add(obj);
            }else if(ExperimentType.CONSTANTLOAD == type){
                EmDataConstantload load = (EmDataConstantload)e;
                JSONObject obj = new JSONObject();
                obj.put("speed100",load.getSpeed100());
                obj.put("torque100",load.getTorque100());
                ary.add(obj);
            }
        }
        return ary;
    }

    /**
     * 下拉框选择时重新加载数据
     * @param request
     * @param productId
     * @return
     */
    @RequestMapping("reloadDatas")
    @ResponseBody
    public Object reloadDatas(HttpServletRequest request,Integer productId){
        return this.getExperimentData(productId,request);
    }

    @ResponseBody
    @RequestMapping("findDefaultParams")
    public Object findDefaultParams(){
        return emBaseParamsService.findDefault();
    }

    @RequestMapping("toCustomParams")
    public ModelAndView toCustomParams(){
        ModelAndView mv = new ModelAndView("em/analysis/customParams");
        return mv;
    }

    @ResponseBody
    @RequestMapping("findCustomParams")
    public Object findCustomParams(HttpServletRequest request){
        PageInfo<EmBaseParams> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("isDefault",0);
        PageInfo<EmBaseParams> pageInfo = emBaseParamsService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

}
