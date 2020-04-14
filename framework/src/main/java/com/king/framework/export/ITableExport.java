package com.king.framework.export;

import com.king.framework.exception.BusinessException;

import java.io.IOException;
import java.util.List;

/**
 * 表格导出
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public interface ITableExport<T> {

    Object export(String author, String title, List<List<Field>> headers, List<T> dataList) throws BusinessException, IOException;

}
