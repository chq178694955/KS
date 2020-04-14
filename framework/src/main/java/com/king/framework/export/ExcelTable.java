package com.king.framework.export;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class ExcelTable {

    private String sheetName;

    private ExcelHeader header;

    private ExcelBody body;

    public ExcelTable(){

    }

    public ExcelTable(ExcelHeader header){
        this.header = header;
    }

    public ExcelTable(ExcelHeader header,ExcelBody body){
        this.header = header;
        this.body = body;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public ExcelHeader getHeader() {
        return header;
    }

    public void setHeader(ExcelHeader header) {
        this.header = header;
    }

    public ExcelBody getBody() {
        return body;
    }

    public void setBody(ExcelBody body) {
        this.body = body;
    }
}
