package com.king.framework.echarts.code;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public enum TriggerType {

    AXIS("axis"),ITEM("item");

    private String val;

    TriggerType(String val){
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }}
