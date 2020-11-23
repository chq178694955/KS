package com.king.em.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmBaseParams implements Serializable {
    private Integer id;

    private BigDecimal fixedCurrent;

    private BigDecimal fixedVoltage;

    private BigDecimal fixedSpeed;

    private BigDecimal fixedTorque;

    private BigDecimal overloadCapacity;

    private BigDecimal machineLength;

    private BigDecimal machineHeight;

    private BigDecimal machineWidth;

    private BigDecimal rotorLength;

    private BigDecimal machineWeight;

    private Integer isDefault;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFixedCurrent() {
        return fixedCurrent;
    }

    public void setFixedCurrent(BigDecimal fixedCurrent) {
        this.fixedCurrent = fixedCurrent;
    }

    public BigDecimal getFixedVoltage() {
        return fixedVoltage;
    }

    public void setFixedVoltage(BigDecimal fixedVoltage) {
        this.fixedVoltage = fixedVoltage;
    }

    public BigDecimal getFixedSpeed() {
        return fixedSpeed;
    }

    public void setFixedSpeed(BigDecimal fixedSpeed) {
        this.fixedSpeed = fixedSpeed;
    }

    public BigDecimal getFixedTorque() {
        return fixedTorque;
    }

    public void setFixedTorque(BigDecimal fixedTorque) {
        this.fixedTorque = fixedTorque;
    }

    public BigDecimal getOverloadCapacity() {
        return overloadCapacity;
    }

    public void setOverloadCapacity(BigDecimal overloadCapacity) {
        this.overloadCapacity = overloadCapacity;
    }

    public BigDecimal getMachineLength() {
        return machineLength;
    }

    public void setMachineLength(BigDecimal machineLength) {
        this.machineLength = machineLength;
    }

    public BigDecimal getMachineHeight() {
        return machineHeight;
    }

    public void setMachineHeight(BigDecimal machineHeight) {
        this.machineHeight = machineHeight;
    }

    public BigDecimal getMachineWidth() {
        return machineWidth;
    }

    public void setMachineWidth(BigDecimal machineWidth) {
        this.machineWidth = machineWidth;
    }

    public BigDecimal getRotorLength() {
        return rotorLength;
    }

    public void setRotorLength(BigDecimal rotorLength) {
        this.rotorLength = rotorLength;
    }

    public BigDecimal getMachineWeight() {
        return machineWeight;
    }

    public void setMachineWeight(BigDecimal machineWeight) {
        this.machineWeight = machineWeight;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}