package com.king.app.webapp.controller.system;

import com.king.framework.base.BaseController;
import com.king.framework.echarts.EchartsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/4/26
 * @描述
 */
@Controller
@RequestMapping("sys/echarts")
public class EchartsController extends BaseController {

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/echarts/main");
        return mv;
    }

    @RequestMapping("getChart")
    @ResponseBody
    public Object getChart(){
        List<String> legendData = new ArrayList<>();
        legendData.add("中国");
        legendData.add("美国");
        List<String> xData = new ArrayList<>();
        xData.add("周一");xData.add("周二");xData.add("周三");xData.add("周四");

        List<Map<String,Object>> seriesData = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map1.put("name","中国");
        map2.put("name","美国");
        List<String> list1 = new ArrayList<>();
        list1.add("100");list1.add("88");list1.add("105");list1.add("125");
        map1.put("data",list1);
        String[] list2 = new String[]{"66","-","99","99"};
        map2.put("data",list2);
        seriesData.add(map1);
        seriesData.add(map2);

        return EchartsUtil.createBar("人口",legendData,"时间",xData,"值",seriesData);
    }

}
