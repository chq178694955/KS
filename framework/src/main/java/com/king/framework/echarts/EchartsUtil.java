package com.king.framework.echarts;


import com.king.framework.echarts.code.EchartsType;
import com.king.framework.echarts.factory.EchartsFactory;
import com.king.framework.echarts.options.impl.BarOption;
import com.king.framework.echarts.options.impl.LineOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public class EchartsUtil {

    private static final Logger logger = LoggerFactory.getLogger(EchartsUtil.class);

    /**
     * 线性图
     * @param text
     * @param legendData
     * @param xName
     * @param xData
     * @param yName
     * @param seriesData
     * @return
     */
    public static Object createLine(String text, List<String> legendData,
                                  String xName, List<String> xData,
                                  String yName,
                                  List<Map<String,Object>> seriesData){
        LineOption line = (LineOption)EchartsFactory.create(EchartsType.LINE);
        line.createOption(text,legendData,xName,xData,yName,seriesData);
        logger.info(line.build().toString());
        return line.build();
    }

    /**
     * 柱狀图
     * @param text
     * @param legendData
     * @param xName
     * @param xData
     * @param yName
     * @param seriesData
     * @return
     */
    public static Object createBar(String text, List<String> legendData,
                                    String xName, List<String> xData,
                                    String yName,
                                    List<Map<String,Object>> seriesData){
        BarOption bar = (BarOption)EchartsFactory.create(EchartsType.BAR);
        bar.createOption(text,legendData,xName,xData,yName,seriesData);
        logger.info(bar.build().toString());
        return bar.build();
    }

    public static void main(String[] args) {
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

        EchartsUtil.createLine("人口",legendData,"时间",xData,"值",seriesData);
    }

}
