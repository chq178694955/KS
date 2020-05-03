package com.king.framework.echarts.code;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public enum EchartsType {

    LINE("line"),BAR("bar"),PIE("pie");

    private String val;

    EchartsType(String val){
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }}
