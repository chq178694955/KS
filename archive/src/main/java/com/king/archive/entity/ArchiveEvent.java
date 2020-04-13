package com.king.archive.entity;

/**
 * 档案日志实体
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class ArchiveEvent extends Archive{

    private Long seq;

    private Integer operatorType;

    private String operatorDesc;

    private Long operator;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorDesc() {
        return operatorDesc;
    }

    public void setOperatorDesc(String operatorDesc) {
        this.operatorDesc = operatorDesc;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }
}
