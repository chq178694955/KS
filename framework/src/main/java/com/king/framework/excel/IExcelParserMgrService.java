package com.king.framework.excel;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
public interface IExcelParserMgrService<T extends ExcelParser> {

    ExcelParserType getParserType();

    T parser(Workbook book);

}
