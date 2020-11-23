package com.king.em.entity;

import java.math.BigDecimal;

public class EmDataConstantload extends Experiment {

    private BigDecimal speed100;

    private BigDecimal torque100;

    private BigDecimal speedSetter;

    private BigDecimal torqueOverload;

    private static final long serialVersionUID = 1L;

    public BigDecimal getSpeed100() {
        return speed100;
    }

    public void setSpeed100(BigDecimal speed100) {
        this.speed100 = speed100;
    }

    public BigDecimal getTorque100() {
        return torque100;
    }

    public void setTorque100(BigDecimal torque100) {
        this.torque100 = torque100;
    }

    public BigDecimal getSpeedSetter() {
        return speedSetter;
    }

    public void setSpeedSetter(BigDecimal speedSetter) {
        this.speedSetter = speedSetter;
    }

    public BigDecimal getTorqueOverload() {
        return torqueOverload;
    }

    public void setTorqueOverload(BigDecimal torqueOverload) {
        this.torqueOverload = torqueOverload;
    }
}