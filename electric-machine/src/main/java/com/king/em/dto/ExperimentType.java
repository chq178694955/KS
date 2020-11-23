package com.king.em.dto;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
public enum ExperimentType {

    UNKNOW(0,"未知"),
    STEP(1,"阶跃"),
    SIN(2,"正弦"),
    OVERLOAD(3,"负载扰动"),
    EMPTYLOAD(4,"空载"),
    SPEED(5,"调速范围测定"),
    CONSTANTLOAD(6,"恒定负载扰动");

    private int val;
    private String name;
    public int getVal(){
        return val;
    }
    public String getName(){
        return name;
    }
    ExperimentType(int val,String name){
        this.val = val;
        this.name = name;
    }

    public static ExperimentType fromValue(int val){
        for(ExperimentType t : ExperimentType.values()){
            if(t.getVal() == val)return t;
        }
        return null;
    }

}
