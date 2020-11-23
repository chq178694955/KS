package com.king.framework.echarts.code;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public enum Position {

    L("left"),C("center"),R("right"),B("bottom"),T("top"),
    H("horizontal"),V("vertical");

    private String val;

    Position(String val){
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }}
