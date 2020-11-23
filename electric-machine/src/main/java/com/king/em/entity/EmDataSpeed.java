package com.king.em.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmDataSpeed extends Experiment {

    private BigDecimal speedMax;

    private BigDecimal speedMin;

    private static final long serialVersionUID = 1L;

    public BigDecimal getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(BigDecimal speedMax) {
        this.speedMax = speedMax;
    }

    public BigDecimal getSpeedMin() {
        return speedMin;
    }

    public void setSpeedMin(BigDecimal speedMin) {
        this.speedMin = speedMin;
    }
}