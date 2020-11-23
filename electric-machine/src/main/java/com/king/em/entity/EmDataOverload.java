package com.king.em.entity;

import java.math.BigDecimal;

public class EmDataOverload extends Experiment {

    private BigDecimal speedEmpty;

    private BigDecimal speedFixedLoad;

    private static final long serialVersionUID = 1L;

    public BigDecimal getSpeedEmpty() {
        return speedEmpty;
    }

    public void setSpeedEmpty(BigDecimal speedEmpty) {
        this.speedEmpty = speedEmpty;
    }

    public BigDecimal getSpeedFixedLoad() {
        return speedFixedLoad;
    }

    public void setSpeedFixedLoad(BigDecimal speedFixedLoad) {
        this.speedFixedLoad = speedFixedLoad;
    }
}