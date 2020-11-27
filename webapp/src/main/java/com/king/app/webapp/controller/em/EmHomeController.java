package com.king.app.webapp.controller.em;

import com.king.app.webapp.dto.ResultResp;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.*;
import com.king.em.factory.ExperimentDataServiceFactory;
import com.king.em.service.IEmProductService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/20
 * @描述
 */
@Controller
@RequestMapping("em/home")
public class EmHomeController extends BaseController {

    @Autowired
    private IEmProductService emProductService;

    @RequestMapping("downloadExcelTemplate")
    @ResponseBody
    public void excel(HttpServletRequest request, HttpServletResponse response){
        try {
            File cfgFile = ResourceUtils.getFile("classpath:experimental-data.xlsx");
            super.downloadFile("实验数据.xlsx", cfgFile.getPath(),response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("importExcelDataPage")
    public ModelAndView importExcelDataPage(){
        ModelAndView mv = new ModelAndView("em/home/importExcelData");
        return mv;
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
        Sheet sheet = book.getSheetAt(0);
        int beginIndex = 3;//数据入库开始行
        int countRow = sheet.getLastRowNum() + 1;

        List<Experiment> list1 = new ArrayList<>();
        List<Experiment> list2 = new ArrayList<>();
        List<Experiment> list3 = new ArrayList<>();
        List<Experiment> list4 = new ArrayList<>();
        List<Experiment> list5 = new ArrayList<>();
        List<Experiment> list6 = new ArrayList<>();
        try{
            for(int i=beginIndex;i<countRow;i++){
                Row row = sheet.getRow(i);
                // step 1
                BigDecimal v1 = this.parseDecimal(this.getCellFormatValue(row.getCell(0)));
                BigDecimal v2 = this.parseDecimal(this.getCellFormatValue(row.getCell(1)));
                if(v1 != null && v2 != null){
                    EmDataStep entity = new EmDataStep();
                    entity.setDataTime(v1);
                    entity.setSpeed(v2);
                    list1.add(entity);
                }
                // sin 2
                BigDecimal v3 = this.parseDecimal(this.getCellFormatValue(row.getCell(2)));
                if(v3 != null){
                    EmDataSin entity = new EmDataSin();
                    entity.setSpeed10(v3);
                    list2.add(entity);
                }
                // overload
                BigDecimal v4 = this.parseDecimal(this.getCellFormatValue(row.getCell(3)));
                BigDecimal v5 = this.parseDecimal(this.getCellFormatValue(row.getCell(4)));
                if(v4 != null && v5 != null){
                    EmDataOverload entity = new EmDataOverload();
                    entity.setSpeedEmpty(v4);
                    entity.setSpeedFixedLoad(v5);
                    list3.add(entity);
                }
                // empty load
                BigDecimal v6 = this.parseDecimal(this.getCellFormatValue(row.getCell(5)));
                BigDecimal v7 = this.parseDecimal(this.getCellFormatValue(row.getCell(6)));
                if(v6 != null && v7 != null){
                    EmDataEmptyload entity = new EmDataEmptyload();
                    entity.setSpeedForward(v6);
                    entity.setSpeedReverse(v7);
                    list4.add(entity);
                }
                // speed
                BigDecimal v8 = this.parseDecimal(this.getCellFormatValue(row.getCell(7)));
                BigDecimal v9 = this.parseDecimal(this.getCellFormatValue(row.getCell(8)));
                if(v8 != null && v9 != null){
                    EmDataSpeed entity = new EmDataSpeed();
                    entity.setSpeedMax(v8);
                    entity.setSpeedMin(v9);
                    list5.add(entity);
                }

                // constant load
                BigDecimal v10 = this.parseDecimal(this.getCellFormatValue(row.getCell(9)));
                BigDecimal v11 = this.parseDecimal(this.getCellFormatValue(row.getCell(10)));
                BigDecimal v12 = this.parseDecimal(this.getCellFormatValue(row.getCell(11)));//转速设定值，只需要一条即可
                BigDecimal v13 = this.parseDecimal(this.getCellFormatValue(row.getCell(12)));//负载转矩，只需要一条即可
                if(v10 != null && v11 != null){
                    EmDataConstantload entity = new EmDataConstantload();
                    entity.setSpeed100(v10);
                    entity.setTorque100(v11);
                    entity.setSpeedSetter(v12);
                    entity.setTorqueOverload(v13);
                    list6.add(entity);
                }
            }
            EmProduct product = emProductService.findByName(filePrefix);
            if(product == null){
                product = new EmProduct();
                product.setCreateTime(new Date());
                product.setName(filePrefix);
                product.setUserId(AuthUtils.getUserInfo().getId());
                emProductService.add(product);
            }else{
                //需要先删除该项目数据
                boolean bol1 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.STEP).deleteByProductId(product.getId());
                boolean bol2 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SIN).deleteByProductId(product.getId());
                boolean bol3 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.OVERLOAD).deleteByProductId(product.getId());
                boolean bol4 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.EMPTYLOAD).deleteByProductId(product.getId());
                boolean bol5 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SPEED).deleteByProductId(product.getId());
                boolean bol6 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.CONSTANTLOAD).deleteByProductId(product.getId());
            }
            if(product != null && product.getId() > 0){
                boolean bol1 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.STEP).batchInsert(parseExperimentList(list1,product.getId()));
                boolean bol2 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SIN).batchInsert(parseExperimentList(list2,product.getId()));
                boolean bol3 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.OVERLOAD).batchInsert(parseExperimentList(list3,product.getId()));
                boolean bol4 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.EMPTYLOAD).batchInsert(parseExperimentList(list4,product.getId()));
                boolean bol5 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.SPEED).batchInsert(parseExperimentList(list5,product.getId()));
                boolean bol6 = ExperimentDataServiceFactory.getInstance().getService(ExperimentType.CONSTANTLOAD).batchInsert(parseExperimentList(list6,product.getId()));
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

    private Long parseLong(Object obj){
        if(obj == null)return null;
        return Long.parseLong(obj.toString());
    }

    private BigDecimal parseDecimal(Object obj){
        if(obj == null)return null;
        return new BigDecimal(obj.toString());
    }

    private List<Experiment> parseExperimentList(List<Experiment> list,Integer productId){
        for(Experiment ex : list){
            ex.setProductId(productId);
        }
        return list;
    }

}
