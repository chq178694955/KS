package com.king.em.dto;

/**
 * 公式枚举类型
 * @创建人 chq
 * @创建时间 2020/11/27
 * @描述
 */
public enum  FormulaType {

    normalizationOne(1,"归一化公式1"),
    normalizationTwo(2,"归一化公式2");

    private int val;

    private String name;

    FormulaType(int val,String name){
        this.val = val;
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static FormulaType fromValue(int val){
        for(FormulaType type : FormulaType.values()){
            if(type.getVal() == val){
                return type;
            }
        }
        return null;
    }
}
