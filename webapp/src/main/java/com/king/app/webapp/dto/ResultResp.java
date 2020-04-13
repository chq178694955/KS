package com.king.app.webapp.dto;

import com.king.framework.utils.I18nUtils;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
public class ResultResp<T> {

    private int code;
    private String msg;
    private T data;

    public ResultResp(){}

    public ResultResp(String msg){
        this.code = 1;
        this.msg = msg;
    }

    public ResultResp(int code){
        this.code = code;
    }

    public ResultResp(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultResp(T t){
        this.code = 0;
        this.data = t;
    }

    public ResultResp(int code,T t){
        this.code = code;
        this.data = t;
    }

    public static ResultResp ok(){
        return new ResultResp(0,I18nUtils.get("com.success"));
    }

    public static ResultResp fail(){
        return new ResultResp(1, I18nUtils.get("com.failure"));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
