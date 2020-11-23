package com.king.em.entity;

import java.math.BigDecimal;
import java.util.Date;

public class EmDataStep extends Experiment {


    private Long dataTime;

    private BigDecimal speed;

    private static final long serialVersionUID = 1L;

    public Long getDataTime() {
        return dataTime;
    }

    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }
}