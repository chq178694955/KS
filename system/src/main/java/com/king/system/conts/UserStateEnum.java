package com.king.system.conts;

import com.king.framework.utils.I18nUtils;

/**
 * @创建人 chq
 * @创建时间 2020/4/18
 * @描述
 */
public enum UserStateEnum {

    NORMAL(0, I18nUtils.get("sys.user.field.state.normal")),
    LOCKED(1, I18nUtils.get("sys.user.field.state.locked"));

    private int val;
    private String txt;

    UserStateEnum(int val,String txt){
        this.val = val;
        this.txt = txt;
    }

    public static String fromValue(int val){
        for(UserStateEnum state : UserStateEnum.values()){
            if(val == state.val){
                return state.txt;
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

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }}
