package com.king.framework.echarts.factory;

import com.king.framework.echarts.code.EchartsType;
import com.king.framework.echarts.options.IOption;
import com.king.framework.echarts.options.impl.BarOption;
import com.king.framework.echarts.options.impl.LineOption;
import com.king.framework.echarts.options.impl.PieOption;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public class EchartsFactory {

    public static IOption create(EchartsType type){
        IOption option = null;
        if(EchartsType.LINE == type){
            option = new LineOption();
        }else if(EchartsType.BAR == type){
            option = new BarOption();
        }else if(EchartsType.PIE == type){
            option = new PieOption();
        }
        return option;
    }

}
