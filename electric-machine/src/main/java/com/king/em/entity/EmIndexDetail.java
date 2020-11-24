package com.king.em.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmIndexDetail implements Serializable {
    private Long id;

    private Long indexId;

    private BigDecimal val;

    private BigDecimal weight;

    private Long groupId;

    /*以下是其他表的附带字段，减少查询*/
    private String groupName;

    private String name;

    private BigDecimal maxVal;

    private BigDecimal minVal;

    private String unit;

    private Integer categoryId;

    private String categoryName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(BigDecimal maxVal) {
        this.maxVal = maxVal;
    }

    public BigDecimal getMinVal() {
        return minVal;
    }

    public void setMinVal(BigDecimal minVal) {
        this.minVal = minVal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}