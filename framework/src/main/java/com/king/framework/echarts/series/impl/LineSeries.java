package com.king.framework.echarts.series.impl;

import com.king.framework.echarts.code.EchartsType;
import com.king.framework.echarts.series.Series;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public class LineSeries<T> extends Series<T> {

    public LineSeries(String name,T data){
        super.setType(EchartsType.LINE.getVal());
        super.setData(data);
        super.setName(name);
    }

}
