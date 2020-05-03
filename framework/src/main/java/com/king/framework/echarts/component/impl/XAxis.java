package com.king.framework.echarts.component.impl;

import com.king.framework.echarts.component.Axis;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public class XAxis<T> extends Axis {

    public XAxis(String name,T data){
        this.setType("category");
        this.setName(name);
        this.setData(data);
    }

}
