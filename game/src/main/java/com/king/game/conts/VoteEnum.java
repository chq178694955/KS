package com.king.game.conts;

import com.king.framework.utils.I18nUtils;

/**
 * @创建人 chq
 * @创建时间 2020/4/15
 * @描述
 */
public enum VoteEnum {

    READY(0,I18nUtils.get("vote.field.status.ready")),
    START(1,I18nUtils.get("vote.field.status.start")),
    PAUSE(2,I18nUtils.get("vote.field.status.pause")),
    FINISH(3,I18nUtils.get("vote.field.status.finish")),
    DISCARD(4,I18nUtils.get("vote.field.status.discard"));

    private int val;

    private String txt;

    VoteEnum(int val,String txt){
        this.val = val;
        this.txt = txt;
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
    }

    public static String getDesc(int val){
        for(VoteEnum vote : VoteEnum.values()){
            if(vote.getVal() == val){
                return vote.txt;
            }
        }
        return null;
    }

}
