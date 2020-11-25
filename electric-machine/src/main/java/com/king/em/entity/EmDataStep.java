package com.king.em.entity;

import java.math.BigDecimal;
import java.util.Date;

public class EmDataStep extends Experiment {


    private BigDecimal dataTime;

    private BigDecimal speed;

    private static final long serialVersionUID = 1L;

    public BigDecimal getDataTime() {
        return dataTime;
    }

    public void setDataTime(BigDecimal dataTime) {
        this.dataTime = dataTime;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }
}