package com.king.keke.statis;

public enum VoteStrategyEnum {

    V0(0,"voteWeightStrategy"),
    V1(1,"voteWeightAreaStrategy"),
    V2(2,"voteWeightManyStrategy"),
    V3(3,"voteWeightAreaManyStrategy");

    private int ruleId;
    private String serviceName;

    VoteStrategyEnum(int ruleId,String serviceName){
        this.ruleId = ruleId;
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return this.serviceName;
    }

    public int getRuleId(){
        return this.ruleId;
    }

    public static String fromRule(int ruleId){
        for(VoteStrategyEnum e : VoteStrategyEnum.values()){
            if(ruleId == e.getRuleId()){
                return e.getServiceName();
            }
        }
        return null;
    }

}
