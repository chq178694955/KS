package com.king.framework.excel;

import java.io.Serializable;

/**
 * 解析对象基类
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
public class ExcelParser implements Serializable,Cloneable {

    private static final long serialVersionUID = 272514803397113187L;

    private ExcelParserType excelParserType;

    public ExcelParser(ExcelParserType excelParserType){
        this.excelParserType = excelParserType;
    }

    public ExcelParserType getExcelParserType() {
        return excelParserType;
    }

    public void setExcelParserType(ExcelParserType excelParserType) {
        this.excelParserType = excelParserType;
    }
}
