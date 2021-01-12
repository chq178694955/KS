package com.king.em.strategy.impl;

import com.king.em.strategy.AbsNormalizationMgrService;

import java.math.BigDecimal;

/**
 * 归一化公式2
 * @创建人 chq
 * @创建时间 2020/11/27
 * @描述
 */
public class NormalizationTwoServiceImpl extends AbsNormalizationMgrService {

    @Override
    public BigDecimal normalization(BigDecimal val, BigDecimal lower, BigDecimal upper) {
        if(val.compareTo(lower) < 0){
            return BigDecimal.ONE;
        }else if(val.compareTo(upper) > 0){
            return BigDecimal.ZERO;
        }else {
            return upper.subtract(val).divide(upper.subtract(lower),BigDecimal.ROUND_HALF_UP,4);
//            return val.subtract(lower).divide(upper.subtract(lower),BigDecimal.ROUND_HALF_UP,4);
        }
    }

}
