package com.king.keke.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResidentVote implements Serializable {

    private String houseHolder;

    private String phone;

    private String building;

    private String houseNo;

    private BigDecimal area;

    private String idCardNo;

    private String remarks;

    private Long residentId;

    private Long voteId;

    private Long voteOptionId;

    private String itemIds;

    private Integer voteStatus;

    private String voteStatusDesc;

    private static final long serialVersionUID = 1L;

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Long voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds == null ? null : itemIds.trim();
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public Integer getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Integer voteStatus) {
        this.voteStatus = voteStatus;
    }

    public String getVoteStatusDesc() {
        return voteStatusDesc;
    }

    public void setVoteStatusDesc(String voteStatusDesc) {
        this.voteStatusDesc = voteStatusDesc;
    }
}