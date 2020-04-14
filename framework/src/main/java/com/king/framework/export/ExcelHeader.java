package com.king.framework.export;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class ExcelHeader {

    private List<List<Field>> headers;

    private int totalRows;

    private int maxCols;

    private List<Field> fields;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public ExcelHeader(List<List<Field>> headers){
        this.headers = headers;

        this.totalRows = headers != null ? headers.size() : 0;

        calcMaxCols(headers);
    }

    public int getMaxCols() {
        return maxCols;
    }

    public void setMaxCols(int maxCols) {
        this.maxCols = maxCols;
    }

    private void calcMaxCols(List<List<Field>> headers) {
        if(headers == null){
            this.maxCols = 0;
        }else{
            for(int i=0;i<headers.size();i++){
                int cols = headers.get(i).size();
                if(cols > this.maxCols){
                    this.maxCols = cols;
                    this.fields = headers.get(i);
                }
            }
        }
    }

    public List<List<Field>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<List<Field>> headers) {
        this.headers = headers;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
