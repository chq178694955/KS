package com.king.em.entity;

import java.math.BigDecimal;

public class EmDataEmptyload extends Experiment {

    private BigDecimal speedForward;

    private BigDecimal speedReverse;

    private static final long serialVersionUID = 1L;

    public BigDecimal getSpeedForward() {
        return speedForward;
    }

    public void setSpeedForward(BigDecimal speedForward) {
        this.speedForward = speedForward;
    }

    public BigDecimal getSpeedReverse() {
        return speedReverse;
    }

    public void setSpeedReverse(BigDecimal speedReverse) {
        this.speedReverse = speedReverse;
    }
}