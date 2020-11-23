package com.king.framework.excel;

/**
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
public enum ExcelParserType{
    UNKNOW(-999999),
    USER(1);
    private int value;
    ExcelParserType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static ExcelParserType fromValue(int value){
        ExcelParserType rs = null;
        for(ExcelParserType e : ExcelParserType.values()){
            if(e.getValue() == value){rs = e;break;}
        }
        return rs;
    }
}
