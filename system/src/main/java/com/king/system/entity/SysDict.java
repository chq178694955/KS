package com.king.system.entity;

import java.io.Serializable;

public class SysDict implements Serializable {
    private Long dictSn;

    private Integer classNo;

    private Integer dictNo;

    private String dictDesc;

    private Integer dispOrder;

    private String parentSn;

    private String useFlag;

    private static final long serialVersionUID = 1L;

    public Long getDictSn() {
        return dictSn;
    }

    public void setDictSn(Long dictSn) {
        this.dictSn = dictSn;
    }

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public Integer getDictNo() {
        return dictNo;
    }

    public void setDictNo(Integer dictNo) {
        this.dictNo = dictNo;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }

    public Integer getDispOrder() {
        return dispOrder;
    }

    public void setDispOrder(Integer dispOrder) {
        this.dispOrder = dispOrder;
    }

    public String getParentSn() {
        return parentSn;
    }

    public void setParentSn(String parentSn) {
        this.parentSn = parentSn == null ? null : parentSn.trim();
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag == null ? null : useFlag.trim();
    }
}