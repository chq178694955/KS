package com.king.framework.echarts.series;

import com.king.framework.echarts.code.EchartsType;
import com.king.framework.echarts.series.impl.BarSeries;
import com.king.framework.echarts.series.impl.LineSeries;
import com.king.framework.echarts.series.impl.PieSeries;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public class SeriesStrategy<T> {

    private Series<T> series;

    public SeriesStrategy(String seriesType,String name,T data){
        if(EchartsType.LINE.getVal().equals(seriesType)){
            this.series = new LineSeries<>(name,data);
        }else if(EchartsType.BAR.getVal().equals(seriesType)){
            this.series = new BarSeries<>(name,data);
        }else if(EchartsType.PIE.getVal().equals(seriesType)){
            this.series = new PieSeries<>(name,data);
        }
    }

    public Series getSeries(){
        return this.series;
    }

}
