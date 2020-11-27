package com.king.em.factory;

import com.king.em.dto.FormulaType;
import com.king.em.strategy.AbsNormalizationMgrService;
import com.king.em.strategy.IFormulaStrategyService;
import com.king.em.strategy.impl.NormalizationOneServiceImpl;
import com.king.em.strategy.impl.NormalizationTwoServiceImpl;

import java.math.BigDecimal;

/**
 * @创建人 chq
 * @创建时间 2020/11/27
 * @描述
 */
public class FormulaMgrServiceFactory {

    private IFormulaStrategyService formulaStrategyService;

    private AbsNormalizationMgrService absNormalizationMgrService;

    private FormulaMgrServiceFactory(){

    };

    private static FormulaMgrServiceFactory instance;

    public static FormulaMgrServiceFactory getInstance(){
        if(null == instance){
            synchronized (FormulaMgrServiceFactory.class){
                if(null == instance){
                    instance = new FormulaMgrServiceFactory();
                }
            }
        }
        return instance;
    }

    public BigDecimal getCalcResult(FormulaType type, BigDecimal val, BigDecimal lower, BigDecimal upper){
        if(type == FormulaType.normalizationOne){
            absNormalizationMgrService = new NormalizationOneServiceImpl();
        }else if(type == FormulaType.normalizationTwo){
            absNormalizationMgrService = new NormalizationTwoServiceImpl();
        }else{
            return BigDecimal.ZERO;
        }
        return absNormalizationMgrService.normalization(val,lower,upper);
    }

}


