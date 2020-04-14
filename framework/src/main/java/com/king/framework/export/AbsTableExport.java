package com.king.framework.export;

import com.king.framework.exception.BusinessException;

import java.io.IOException;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public abstract class AbsTableExport<T> implements ITableExport<T> {

    public AbsTableExport(){

    }

    @Override
    public Object export(String author, String title, List<List<Field>> headers, List<T> dataList) throws IOException {
        ExcelTable table = this.createTable(title,headers, dataList);
        return this.doExport(author,table);
    }

    protected ExcelTable createTable(String title, List<List<Field>> headers, List<?> datas){
        ExcelHeader header = new ExcelHeader(headers);
        ExcelBody body = new ExcelBody(datas);
        ExcelTable table = new ExcelTable(header,body);
        table.setSheetName(title);
        return table;
    }

    protected abstract Object doExport(String author,ExcelTable excelTable) throws IOException;

}
