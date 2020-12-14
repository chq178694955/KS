package com.king.app.webapp.controller.em;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.em.dto.ExperimentType;
import com.king.em.dto.FormulaType;
import com.king.em.entity.*;
import com.king.em.factory.ExperimentDataServiceFactory;
import com.king.em.factory.FormulaMgrServiceFactory;
import com.king.em.service.*;
import com.king.em.util.EmCalcUtil;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.system.utils.AuthUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private IEmIndexCategoryService emIndexCategoryService;
    @Autowired
    private IEmIndexTemplateService emIndexTemplateService;
    @Autowired
    private IEmIndexGroupService emIndexGroupService;
    @Autowired
    private IEmIndexDetailService emIndexDetailService;

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
            Map<String,Object> experimentDataObj = this.getExperimentData(productId);
            mv.addObject("experimentDataObj",experimentDataObj);
        }else{
            Map<String,Object> experimentDataObj = new HashMap<>();
            experimentDataObj.put("hasDataFlag",false);
            experimentDataObj.put("msg","暂无试验数据");
            experimentDataObj.put("stepList",new JSONArray());
            experimentDataObj.put("sinList",new JSONArray());
            experimentDataObj.put("emptyloadList",new JSONArray());
            experimentDataObj.put("constantloadList",new JSONArray());
            mv.addObject("experimentDataObj",experimentDataObj);
        }
        // 指标相关
        List<EmIndexCategory> categories = emIndexCategoryService.findAll();
        List<EmIndexTemplate> templates = emIndexTemplateService.findAll();
        mv.addObject("categories",categoryToAry(categories));
        mv.addObject("templates",templateToAry(templates));
        return mv;
    }

    /**
     * 获取实验数据
     * productId:本次实验项目ID
     * @param productId
     * @return
     */
    private Map<String,Object> getExperimentData(Integer productId){
        Map<String,Object> result = new HashMap<>();
        Criteria criteria = new Criteria();
        criteria.put("productId",productId);

        StringBuffer errorMsg = new StringBuffer();

        List<Experiment> stepList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.STEP).findAll(criteria);
        List<Experiment> sinList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SIN).findAll(criteria);
        List<Experiment> overloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.OVERLOAD).findAll(criteria);
        List<Experiment> emptyloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.EMPTYLOAD).findAll(criteria);
        List<Experiment> speedList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SPEED).findAll(criteria);
        List<Experiment> constantloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.CONSTANTLOAD).findAll(criteria);
        result.put("stepList",dataToAry(ExperimentType.STEP,stepList));//取全部数据
        result.put("sinList",new JSONArray());
        result.put("constantloadList",new JSONArray());
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
            errorMsg.append("实验数据不完整，请重新导入！");
        }
        result.put("msg",errorMsg.toString());

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

    private JSONArray categoryToAry(List<EmIndexCategory> categories){
        JSONArray ary = new JSONArray();
        for(EmIndexCategory category : categories){
            JSONObject obj = new JSONObject();
            obj.put("id",category.getId());
            obj.put("name",category.getName());
            ary.add(obj);
        }
        return ary;
    }

    private JSONArray templateToAry(List<EmIndexTemplate> templates){
        JSONArray ary = new JSONArray();
        for(EmIndexTemplate template : templates){
            JSONObject obj = new JSONObject();
            obj.put("id",template.getId());
            obj.put("name",template.getName());
            obj.put("bestVal",template.getBestVal());
            obj.put("maxVal",template.getMaxVal());
            obj.put("minVal",template.getMinVal());
            obj.put("weight",template.getWeight());
            obj.put("unit",template.getUnit());
            obj.put("categoryId",template.getCategoryId());
            obj.put("formulaId",template.getFormulaId());
            if(template.getCalcVal() != null){
                obj.put("calcVal",template.getCalcVal());
            }
            if(template.getNormalVal() != null){
                obj.put("normalVal",template.getNormalVal());
            }
            ary.add(obj);
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
        return this.getExperimentData(productId);
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

    @RequestMapping("toCustomIndex")
    public ModelAndView toCustomIndex(){
        ModelAndView mv = new ModelAndView("em/analysis/customIndex");
        return mv;
    }

    @ResponseBody
    @RequestMapping("findIndexGroup")
    public Object findIndexGroup(HttpServletRequest request){
        PageInfo<EmIndexGroup> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("userId", AuthUtils.getUserInfo().getId());//带入条件，只查询属于自己创建的指标
        PageInfo<EmIndexGroup> pageInfo = emIndexGroupService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @ResponseBody
    @RequestMapping("findCustomIndex")
    public Object findCustomIndex(HttpServletRequest request,Long groupId){
        return emIndexDetailService.findByGroupId(groupId);
    }


    @ResponseBody
    @RequestMapping("startEstimate")
    public Object startEstimate(HttpServletRequest request){
        JSONObject result = new JSONObject();//结果输出

        Integer productId = super.getIntegerParam("productId");
        //获取基本参数
        BigDecimal fixedCurrent = super.getDecimalParam("fixedCurrent");
        BigDecimal fixedVoltage = super.getDecimalParam("fixedVoltage");
        BigDecimal fixedSpeed = super.getDecimalParam("fixedSpeed");
        BigDecimal fixedTorque = super.getDecimalParam("fixedTorque");
        BigDecimal overloadCapacity = super.getDecimalParam("overloadCapacity");
        BigDecimal machineLength = super.getDecimalParam("machineLength");
        BigDecimal machineWidth = super.getDecimalParam("machineWidth");
        BigDecimal machineHeight = super.getDecimalParam("machineHeight");
        BigDecimal machineWeight = super.getDecimalParam("machineWeight");
        BigDecimal rotorLength = super.getDecimalParam("rotorLength");
        //获取指标
        List<EmIndexCategory> categories = emIndexCategoryService.findAll();
        List<EmIndexTemplate> templates = emIndexTemplateService.findAll();//根据指标模板获取对应指标内容
        result.put("categories",categoryToAry(categories));
        //指标值命名方式"val_指标ID"，指标权重命名方式"weight_指标ID"
        //将实际指标和权重存入map当中，任然已上面的命名方式作为key
        Map<String,BigDecimal> mapIndex = new HashMap<>();
        templates.stream().forEach(e->{
            mapIndex.put("val_" + e.getId(),super.getDecimalParam("val_" + e.getId()));
            mapIndex.put("weight_" + e.getId(),super.getDecimalParam("weight_" + e.getId()));
        });

        Criteria criteria = new Criteria();
        criteria.put("productId",productId);
        List<Experiment> stepList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.STEP).findAll(criteria);
        List<Experiment> sinList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SIN).findAll(criteria);
        List<Experiment> overloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.OVERLOAD).findAll(criteria);
        List<Experiment> emptyloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.EMPTYLOAD).findAll(criteria);
        List<Experiment> speedList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SPEED).findAll(criteria);
        List<Experiment> constantloadList = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.CONSTANTLOAD).findAll(criteria);
        //判断数据是否完整
        if(!CollectionUtils.isEmpty(sinList) || !CollectionUtils.isEmpty(sinList) || !CollectionUtils.isEmpty(overloadList)
                || !CollectionUtils.isEmpty(emptyloadList) || !CollectionUtils.isEmpty(speedList) || !CollectionUtils.isEmpty(constantloadList)){
            final List<Experiment> sinList2 = new ArrayList<>();
            sinList.stream().limit(10).forEach(k->{sinList2.add(k);});
            final List<Experiment> constantloadList2 = new ArrayList<>();
            constantloadList.stream().limit(100).forEach(k->{constantloadList2.add(k);});

            BigDecimal speedEmpty = ((EmDataOverload)overloadList.get(0)).getSpeedEmpty();//只需要取第1条，空载转速
            BigDecimal speedFixedLoad = ((EmDataOverload)overloadList.get(0)).getSpeedFixedLoad();//只需要取第1条，额定负载转速

            BigDecimal speedMax = ((EmDataSpeed)speedList.get(0)).getSpeedMax();//只需要取第1条 max，最大转速
            BigDecimal speedMin = ((EmDataSpeed)speedList.get(0)).getSpeedMin();//只需要取第1条 min，最小转速

            BigDecimal speedSetter= ((EmDataConstantload)constantloadList.get(0)).getSpeedSetter();//转速设定值
            BigDecimal torqueOverload = ((EmDataConstantload)constantloadList.get(0)).getTorqueOverload();//负载转矩

            //**************** 计算指标 ****************
            //*** 注意，计算后的值必须填入指标模板当中，存在一个写死的过程 ***
            //求出稳态转速
            BigDecimal steadySpeed = EmCalcUtil.getSteadySpeed(stepList);
            if(steadySpeed == null){
                return new ResultResp<>("稳态转速为空！");
            }
            mapIndex.put("calc_0",steadySpeed);
            //求出调整时间
            BigDecimal adjustTime = EmCalcUtil.getAdjustTime(stepList,steadySpeed);
            if(steadySpeed == null){
                return new ResultResp<>("调整时间为空！");
            }
            mapIndex.put("calc_1",adjustTime);
            //求出峰值对象
            EmDataStep peakStep = EmCalcUtil.getPeakTime(stepList);
            if(peakStep == null){
                return new ResultResp<>("峰值时间为空！");
            }
            //峰值时间
            BigDecimal peakTime = peakStep.getDataTime().setScale(BigDecimal.ROUND_HALF_UP,2);
            mapIndex.put("calc_2",peakTime);
            //求出超调量
            BigDecimal overshoot = EmCalcUtil.getOvershoot(peakStep.getSpeed(),steadySpeed);
            mapIndex.put("calc_3",overshoot);
            //转速调整率
            BigDecimal speedAdjustRatio = EmCalcUtil.getSpeedAdjustRatio(sinList2,fixedSpeed);
            mapIndex.put("calc_4",speedAdjustRatio);
            //求出转速变换率
            BigDecimal speedChangeRatio = EmCalcUtil.getSpeedChangeRatio(((EmDataOverload)overloadList.get(0)));
            mapIndex.put("calc_5",speedChangeRatio);
            //正反转速差率
            BigDecimal forwardReverseSpeedDiff = EmCalcUtil.getForwardReverseSpeedDiff(emptyloadList);
            mapIndex.put("calc_6",forwardReverseSpeedDiff);
            //求出调速范围
            EmDataSpeed emDataSpeed = (EmDataSpeed)speedList.get(0);
            BigDecimal speedRange = EmCalcUtil.getSpeedRange(emDataSpeed.getSpeedMax(),emDataSpeed.getSpeedMin());
            mapIndex.put("calc_7",speedRange);
            //过载能力（默认参数）overloadCapacity
            mapIndex.put("calc_8",overloadCapacity);
            //转速平均误差
            BigDecimal speedAvgError = EmCalcUtil.getSpeedAvgError(constantloadList2,speedSetter);
            mapIndex.put("calc_9",speedAvgError);
            //转矩平均误差
            BigDecimal torqueAvgError = EmCalcUtil.getTorqueAvgError(constantloadList2,torqueOverload);
            mapIndex.put("calc_10",torqueAvgError);
            //转速波动系数
            BigDecimal speedCoeff = EmCalcUtil.getSpeedCoeff(constantloadList2);
            mapIndex.put("calc_11",speedCoeff);
            //转矩波动系数
            BigDecimal torqueCoeff = EmCalcUtil.getTorqueCoeff(constantloadList2);
            mapIndex.put("calc_12",torqueCoeff);
            //功率密度
            BigDecimal powerDensity = EmCalcUtil.getPowerDensity(fixedSpeed,fixedTorque,machineWeight);
            mapIndex.put("calc_13",powerDensity);
            //电机体积 长 * 宽 * 高
            BigDecimal volume = machineLength.multiply(machineWidth).multiply(machineHeight).setScale(BigDecimal.ROUND_HALF_UP,2);
            mapIndex.put("calc_14",volume);
            //电机质量（默认参数）machineWeight
            mapIndex.put("calc_15",machineWeight.setScale(BigDecimal.ROUND_HALF_UP,2));
            //轴向尺寸 = 电机机身长度 + 转子轴前端长度
            BigDecimal axialLength = machineLength.add(rotorLength).setScale(BigDecimal.ROUND_HALF_UP,2);
            mapIndex.put("calc_16",axialLength);

            List<EmIndexTemplate> newTemplates = templates.stream().map(t-> {
                t.setCalcVal(mapIndex.get("calc_" + t.getId()));
                return t;
            }).collect(Collectors.toList());

            //最优指标当中最后一个指标
            EmIndexTemplate lastTemplate = newTemplates.stream().reduce((first,second)->second).orElse(null);

            //*** 归一化处理 ***
            // 1.系统预设指标不进行归一化处理和加权、因为它已经做过处理了
            // 2.自定义指标需要进行归一化处理和加权
            int radioIndex = super.getIntegerParam("radioIndex");//1-系统预设 0-自定义

            for(EmIndexTemplate template : newTemplates){
                BigDecimal val = mapIndex.get("calc_" + template.getId());
                BigDecimal lower = template.getMinVal();
                BigDecimal upper = template.getMaxVal();
                if(radioIndex == 1){
                    mapIndex.put("normalVal_" + template.getId(),template.getBestVal());
                    template.setNormalVal(template.getBestVal());
                }else{
                    //获取归一化后的值
                    BigDecimal normalizationVal = FormulaMgrServiceFactory.getInstance().getCalcResult(FormulaType.fromValue(template.getFormulaId()),val,lower,upper);
                    BigDecimal _weight = mapIndex.get("weight_" + template.getId());
                    //存入map方便后续
                    mapIndex.put("normalVal_" + template.getId(),normalizationVal.multiply(_weight));
                    //因图像只需要展示归一化后的数据，不需要乘以权重，特此修改
                    //template.setNormalVal(normalizationVal.multiply(_weight));
                    template.setNormalVal(normalizationVal);
                }
            }

            result.put("templates",templateToAry(newTemplates));//保存计算后的指标值

            //最后一个归一化指标值
            BigDecimal lastNormalVal = mapIndex.get("normalVal_" + lastTemplate.getId());
            //后续计算都需要用归一化的值进行计算，所以重新开一个循环，循环指标
            for(EmIndexTemplate template : newTemplates){
                BigDecimal curNormalVal = mapIndex.get("normalVal_" + template.getId());
                // 最后一个归一化指标值 - 当前归一化指标值 的绝对值
                BigDecimal normalDiffVal = lastNormalVal.subtract(curNormalVal).abs();
                mapIndex.put("normalDiffVal_" + template.getId(),normalDiffVal);
            }
            //求出指标差值中最大和最小值（综合）
            EmIndexTemplate maxTemplate = newTemplates.stream().reduce((first,second)-> mapIndex.get("normalDiffVal_" + first.getId()).compareTo(mapIndex.get("normalDiffVal_" + second.getId())) > 0 ? first : second ).orElse(null);
            EmIndexTemplate minTemplate = newTemplates.stream().reduce((first,second)-> mapIndex.get("normalDiffVal_" + first.getId()).compareTo(mapIndex.get("normalDiffVal_" + second.getId())) < 0 ? first : second ).orElse(null);
            BigDecimal maxNormalDiffVal = mapIndex.get("normalDiffVal_" + maxTemplate.getId());
            BigDecimal minNormalDiffVal = mapIndex.get("normalDiffVal_" + minTemplate.getId());

            //计算关联度（综合）
            BigDecimal degree = BigDecimal.ZERO;

            //计算各指标归一化后的关联系数（综合）
            for(EmIndexTemplate template : newTemplates){
                BigDecimal normalDiffCoeff = minNormalDiffVal.add(new BigDecimal(0.5).multiply(maxNormalDiffVal)).divide(mapIndex.get("normalDiffVal_" + template.getId()).add(new BigDecimal(0.5).multiply(maxNormalDiffVal)),BigDecimal.ROUND_HALF_UP,4);
                //mapIndex.put("normalDiffCoeff_" + template.getId(),normalDiffCoeff);
                degree = degree.add(normalDiffCoeff.multiply(mapIndex.get("weight_" + template.getId())));
            }

            //综合评级
            String evaluation = EmCalcUtil.getComprehensiveEvaluation(degree);
            result.put("evaluation",evaluation);

            //求出指标差值中最大和最小值（分类）
            categories.stream().forEach(c->{
                //当前分类的集合
                List<EmIndexTemplate> curTemplates = newTemplates.stream().filter(e->e.getCategoryId() == c.getId()).collect(Collectors.toList());
                EmIndexTemplate maxCategoryTemplate = curTemplates.stream().reduce((first, second)-> mapIndex.get("normalDiffVal_" + first.getId()).compareTo(mapIndex.get("normalDiffVal_" + second.getId())) > 0 ? first : second ).orElse(null);
                EmIndexTemplate minCategoryTemplate = curTemplates.stream().reduce((first,second)-> mapIndex.get("normalDiffVal_" + first.getId()).compareTo(mapIndex.get("normalDiffVal_" + second.getId())) < 0 ? first : second ).orElse(null);
                //当前分类的最大和最小值
                final BigDecimal maxCategoryNormalDiffVal = mapIndex.get("normalDiffVal_" + maxCategoryTemplate.getId());
                final BigDecimal minCategoryNormalDiffVal = mapIndex.get("normalDiffVal_" + minCategoryTemplate.getId());

                final List<BigDecimal> sumList = new ArrayList<>();
                curTemplates.stream().forEach(e->{
                    BigDecimal normalDiffCoeff = minCategoryNormalDiffVal.add(new BigDecimal(0.5).multiply(maxCategoryNormalDiffVal)).divide(mapIndex.get("normalDiffVal_" + e.getId()).add(new BigDecimal(0.5).multiply(maxCategoryNormalDiffVal)),BigDecimal.ROUND_HALF_UP,4);
                    sumList.add(normalDiffCoeff.multiply(mapIndex.get("weight_" + e.getId())));
                });
                BigDecimal total = sumList.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
                //分类评级
                String cateEvaluation = EmCalcUtil.getComprehensiveEvaluation(total);
                result.put("evaluation_" + c.getId(),cateEvaluation);
            });

            //归一化的数据进行图表展示“normalVal_”

            return new ResultResp<>(result);
        }else{
            return new ResultResp<>("实验数据不完整，请重新导入！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "downloadResult",method = RequestMethod.POST)
    public void downloadResult(HttpServletRequest request, HttpServletResponse response){
        try {
            String evaluation = super.getParam("evaluation");
            String evaluation1 = super.getParam("evaluation_1");
            String evaluation2 = super.getParam("evaluation_2");
            String evaluation3 = super.getParam("evaluation_3");
            File resultFile = ResourceUtils.getFile("classpath:evaluation-result.xlsx");
            String fileName = resultFile.getPath();
            String fileExt = fileName.substring(fileName.lastIndexOf("."));
            InputStream is = new FileInputStream(resultFile);
            Workbook book = null;
            if(".xls".equals(fileExt)){
                book = new HSSFWorkbook(is);
            }else if(".xlsx".equals(fileExt)){
                book = new XSSFWorkbook(is);
            }
            Sheet sheet = book.getSheetAt(0);//获取第一个sheet

            Row row2 = sheet.getRow(2);
            Row row3 = sheet.getRow(3);
            Row row4 = sheet.getRow(4);
            Row row5 = sheet.getRow(5);
            Row row6 = sheet.getRow(6);
            Row row7 = sheet.getRow(7);

            row2.getCell(1).setCellValue(super.getParam("calc_1"));
            row3.getCell(1).setCellValue(super.getParam("calc_2"));
            row4.getCell(1).setCellValue(super.getParam("calc_3"));
            row5.getCell(1).setCellValue(super.getParam("calc_4"));
            row6.getCell(1).setCellValue(super.getParam("calc_5"));
            row7.getCell(1).setCellValue(super.getParam("calc_6"));

            row2.getCell(4).setCellValue(super.getParam("calc_7"));
            row3.getCell(4).setCellValue(super.getParam("calc_8"));
            row4.getCell(4).setCellValue(super.getParam("calc_9"));
            row5.getCell(4).setCellValue(super.getParam("calc_10"));
            row6.getCell(4).setCellValue(super.getParam("calc_11"));
            row7.getCell(4).setCellValue(super.getParam("calc_12"));

            Row row10 = sheet.getRow(10);
            Row row11 = sheet.getRow(11);
            Row row12 = sheet.getRow(12);
            Row row13 = sheet.getRow(13);

            row10.getCell(1).setCellValue(super.getParam("calc_13"));
            row11.getCell(1).setCellValue(super.getParam("calc_14"));
            row12.getCell(1).setCellValue(super.getParam("calc_15"));
            row13.getCell(1).setCellValue(super.getParam("calc_16"));

            row10.getCell(4).setCellValue(evaluation);
            row11.getCell(4).setCellValue(evaluation1);
            row12.getCell(4).setCellValue(evaluation2);
            row13.getCell(4).setCellValue(evaluation3);

            //生成图表
            /*
            String imgUrl = super.getParam("imgUrl");
            if(!StringUtils.isEmpty(imgUrl)){
                String[] imgUrlArr = imgUrl.split("base64,");
                Base64 decode = new Base64();
                byte[] buffer = decode.decode(imgUrlArr[1]);
                String picPath = request.getRealPath("")+ "/"+ UUID.randomUUID().toString() +".png";
                File file = new File(picPath);//图片文件
                //生成图片
                OutputStream out = new FileOutputStream(file);//图片输出流
                out.write(buffer);
                out.flush();//清空流
                out.close();//关闭流
                ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // 将图片写入流中
                BufferedImage bufferImg = ImageIO.read(new File(picPath));
                ImageIO.write(bufferImg, "PNG", outStream); // 利用HSSFPatriarch将图片写入EXCEL
                Drawing patri = sheet.createDrawingPatriarch();
                ClientAnchor anchor = null;
                if(".xls".equals(fileExt)){
                    anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 6, 1, (short) 13, 8);
                }else if(".xlsx".equals(fileExt)){
                    anchor = new XSSFClientAnchor(0, 0, 0, 0,(short) 6, 1, (short) 13, 8);
                }
                patri.createPicture(anchor, book.addPicture(outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
                if(file.exists()){
                    file.delete();//删除图片
                }
            }
            */

            super.downloadFile("评估结果.xlsx", book,response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
