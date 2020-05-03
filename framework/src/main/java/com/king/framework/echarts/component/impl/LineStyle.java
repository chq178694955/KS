package com.king.framework.echarts.component.impl;

import com.king.framework.echarts.component.Style;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public class LineStyle extends Style {

    private String color;

    private int width;

    private String type;

    public LineStyle(){
        this.color = "#48b";
        this.width = 2;
        this.type = "solid";//solid dotted dashed curve broken
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
