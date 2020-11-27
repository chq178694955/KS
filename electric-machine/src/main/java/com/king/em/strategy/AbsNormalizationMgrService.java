package com.king.em.strategy;

import java.math.BigDecimal;

/**
 * @创建人 chq
 * @创建时间 2020/11/27
 * @描述
 */
public abstract class AbsNormalizationMgrService implements IFormulaStrategyService {

    public abstract  BigDecimal normalization(BigDecimal val, BigDecimal lower, BigDecimal upper);

}
