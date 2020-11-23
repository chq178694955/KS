package com.king.framework.model;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public enum TokenStatus {

    EXPIRED("expired"),INVALID("invalid"),VALID("valid");

    private String status;

    TokenStatus(String status){
        this.status = status;
    }

    public String getVal(){
        return this.status;
    }

}
