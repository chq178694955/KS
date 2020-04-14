package com.king.framework.export;

/**
 * 属性类型
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public enum FieldType {

    NUMBERIC(0,"数值型"),
    STRING(1,"字符串"),
    FLOAT(2,"浮点型"),
    DATE(3,"日期型");

    private int val;

    private String desc;

    FieldType(int val,String desc){
        this.val = val;
        this.desc = desc;
    }

    public static String getName(int val){
        for(FieldType fieldType : FieldType.values()){
            if(fieldType.getVal() == val){
                return fieldType.desc;
            }
        }
        return null;
    }

    public static FieldType getType(int val){
        for(FieldType fieldType : FieldType.values()){
            if(fieldType.getVal() == val){
                return fieldType;
            }
        }
        return null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}
