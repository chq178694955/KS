package com.king.keke.entity;

import java.io.Serializable;

public class Resident implements Serializable {
    private Long id;

    private String houseHolder;

    private String phone;

    private String building;

    private String houseNo;

    private Double area;

    private String carNo;

    private String idCardNo;

    private String remarks;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseHolder() {
        return houseHolder;
    }

    public void setHouseHolder(String houseHolder) {
        this.houseHolder = houseHolder == null ? null : houseHolder.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo == null ? null : houseNo.trim();
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}