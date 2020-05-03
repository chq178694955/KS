package com.king.framework.echarts.options.impl;

import com.alibaba.fastjson.JSONArray;
import com.king.framework.echarts.code.EchartsType;
import com.king.framework.echarts.code.Position;
import com.king.framework.echarts.component.*;
import com.king.framework.echarts.component.impl.LineAxisPointer;
import com.king.framework.echarts.component.impl.XAxis;
import com.king.framework.echarts.component.impl.YAxis;
import com.king.framework.echarts.options.AbstractOption;
import com.king.framework.echarts.series.Series;
import com.king.framework.echarts.series.SeriesStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public class LineOption extends AbstractOption {

    @Override
    public void createOption(String text, List<String> legendData,
                             String xName, List<String> xData,
                             String yName,
                             List<Map<String,Object>> seriesData) {
        Title title = new Title(text);
        title.setX(Position.L.getVal()).setY(Position.T.getVal()).setShow(false);
        this.setTitle(title);

        AxisPointer lineAxisPointer = new LineAxisPointer();
        lineAxisPointer.setType(AxisPointer.AxisPointerType.LINE.getVal());
        lineAxisPointer.buildStyle();
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger("axis") // item axis
                .setAxisPointer(lineAxisPointer);
        this.setTooltip(tooltip);

        Legend<List<String>> legend = new Legend<>();
        legend.setOrient("horizontal") // horizontal vertical
                .setX(Position.C.getVal())
                .setY(Position.T.getVal())
                .setData(legendData);
        this.setLegend(legend);

        Axis<List<String>> xAxis = new XAxis<>(xName,xData);
        Axis yAxis = new YAxis(yName);
        this.setxAxis(xAxis);
        this.setyAxis(yAxis);

        List<Series<List<String>>> series = new ArrayList<>();
        if(null != seriesData){
            for(Map<String,Object> map : seriesData){
                String name = null;
                Object data = null;
                Iterator<String> it = map.keySet().iterator();
                while(it.hasNext()){
                    String key = it.next();
                    Object obj = map.get(key);
                    if(obj instanceof String){
                        name = obj.toString();
                    }else if(obj instanceof List){
                        data = obj;
                    }else if(obj instanceof JSONArray){
                        data = obj;
                    }else if(obj instanceof String[]){
                        data = obj;
                    }
                }
                SeriesStrategy<Object> strategy = new SeriesStrategy<>(EchartsType.LINE.getVal(),name,data);
                series.add(strategy.getSeries());
            }
        }
        this.setSeries(series);
    }

}
