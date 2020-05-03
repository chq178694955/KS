package com.king.framework.echarts.options;

import com.alibaba.fastjson.JSONObject;
import com.king.framework.echarts.component.Axis;
import com.king.framework.echarts.component.Legend;
import com.king.framework.echarts.component.Title;
import com.king.framework.echarts.component.Tooltip;

import java.util.List;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public abstract class AbstractOption implements IOption {

    private Title title;

    private Tooltip tooltip;

    private Legend legend;

    private Axis xAxis;

    private Axis yAxis;

    private List series;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Axis getxAxis() {
        return xAxis;
    }

    public void setxAxis(Axis xAxis) {
        this.xAxis = xAxis;
    }

    public Axis getyAxis() {
        return yAxis;
    }

    public void setyAxis(Axis yAxis) {
        this.yAxis = yAxis;
    }

    public List getSeries() {
        return series;
    }

    public void setSeries(List series) {
        this.series = series;
    }

    @Override
    public Object build() {
        return JSONObject.toJSON(this).toString();
    }

    /**
     * 创建option
     * @param text 标题
     * @param legendData 图例数据
     * @param xName x轴名称
     * @param xData x轴数据
     * @param yName y轴名称
     * @param seriesData 图表数据
     */
    public abstract void createOption(String text,List<String> legendData,
                                      String xName, List<String> xData,
                                      String yName,
                                      List<Map<String,Object>> seriesData);
}
