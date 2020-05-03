package com.king.framework.echarts.component.impl;

import com.king.framework.echarts.component.Axis;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public class YAxis extends Axis {

    public YAxis(String name){
        this.setType("value");
        this.setName(name);
    }

}
