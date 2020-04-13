package com.king.archive.dto;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class ArchiveEventDto extends ArchiveDto {

    private Long seq;

    /**
     * 操作类型： 1-新增 2-修改 3-删除
     */
    private Integer operatorType;

    private String operatorDesc;

    private Long operator;

    public ArchiveEventDto(){
        super(ArchiveType.UNKONW);
    }

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
