package com.king.app.webapp.controller.keke;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.*;
import com.king.em.factory.ExperimentDataServiceFactory;
import com.king.framework.base.BaseController;
import com.king.framework.utils.DateUtils;
import com.king.system.utils.AuthUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/20
 * @描述
 */
@Controller
@RequestMapping("keke/resident")
public class ResidentController extends BaseController {

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("keke/resident/main");
        return mv;
    }

    @RequestMapping("find")
    @ResponseBody
    public Object find(HttpServletRequest request){
        PageInfo page = getPage(request);
        return this.getGridData(page);
    }

    @RequestMapping("uploadExcelData")
    @ResponseBody
    public Object uploadExcelData(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return new ResultResp<>("请导入Excel文件");
        }
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        String filePrefix = fileName.substring(0,fileName.lastIndexOf("."));
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = file.getInputStream();
        Workbook book = null;
        if(".xls".equals(fileExt)){
            book = new HSSFWorkbook(is);
        }else if(".xlsx".equals(fileExt)){
            book = new XSSFWorkbook(is);
        }
        if(book == null){
            return new ResultResp<>("请导入Excel文件");
        }
        int sheetCount = book.getNumberOfSheets();//获取sheet数量
        try{
            for(int i=0;i<sheetCount;i++){
                Sheet sheet = book.getSheetAt(i);
                int beginIndex = 3;//数据入库开始行
                int countRow = sheet.getLastRowNum() + 1;//循环的最大行数
                for(int j=beginIndex;j<countRow;j++){
                    Row row = sheet.getRow(j);
                    Object val1 = this.getCellFormatValue(row.getCell(1));
                    Object val2 = this.getCellFormatValue(row.getCell(2));
                    Object val3 = this.getCellFormatValue(row.getCell(3));
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResultResp<>("导入Excel文件异常！");
        }
        return ResultResp.ok();
    }

    private Object getCellFormatValue(Cell cell){
        Object value = null;
        if(cell != null){
            switch (cell.getCellType()){
                case XSSFCell.CELL_TYPE_NUMERIC://数字
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        Date date = cell.getDateCellValue();
                        if(date != null){
                            value = DateUtils.format(date,"yyyy-MM-dd HH:mm");
                        }else{
                            value = null;
                        }
                    }else{
                        value = new DecimalFormat("##########.####").format(cell.getNumericCellValue());
                    }
                    break;
                case XSSFCell.CELL_TYPE_FORMULA://公式
//                    value = cell.getCellFormula() + "";
                    value = null;
                    break;
                case XSSFCell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                default:
                    value = null;
                    break;
            }
        }
        return value;
    }

}
