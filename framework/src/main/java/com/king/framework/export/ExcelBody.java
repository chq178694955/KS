package com.king.framework.export;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class ExcelBody {

    List<?> datas;

    private int totalRows;

    public ExcelBody(List<?> datas){
        this.datas = datas;
        this.totalRows = datas != null ? datas.size() : 0;
    }

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
}
