package com.king.system.parser;

import com.king.framework.excel.ExcelParser;
import com.king.framework.excel.ExcelParserType;
import com.king.framework.excel.IExcelParserMgrService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

/**
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
@Service("sysUserExcelParserService")
public class SysUserExcelParserServiceImpl implements IExcelParserMgrService<ExcelParser> {

    @Override
    public ExcelParserType getParserType() {
        return ExcelParserType.USER;
    }

    @Override
    public ExcelParser parser(Workbook book) {
        return null;
    }

}
