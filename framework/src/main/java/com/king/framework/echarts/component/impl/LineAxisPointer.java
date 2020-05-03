package com.king.framework.echarts.component.impl;

import com.king.framework.echarts.component.AxisPointer;
import com.king.framework.echarts.component.Style;

/**
 * @创建人 chq
 * @创建时间 2020/5/2
 * @描述
 */
public class LineAxisPointer extends AxisPointer {

    private Style lineStyle;

    @Override
    public void buildStyle() {
        lineStyle = new LineStyle();
    }

    public Style getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(Style lineStyle) {
        this.lineStyle = lineStyle;
    }
}
