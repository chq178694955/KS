package com.king.keke.controller;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.framework.utils.DateUtils;
import com.king.keke.entity.CarInfo;
import com.king.keke.entity.ResidentInfo;
import com.king.keke.service.ICarInfoService;
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
import org.springframework.util.StringUtils;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("keke/car")
public class CarController extends BaseController {

    @Autowired
    private ICarInfoService carInfoService;

    @RequestMapping("toMain")
    public ModelAndView toMain(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("keke/car/main");
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

        List<CarInfo> dbList = carInfoService.findAll();
        Map<String,CarInfo> dbMap = dbList.stream().collect(Collectors.toMap(k->{
            return k.getCarNumber() + k.getOwner();
        },part -> part));

        List<CarInfo> insertList = new ArrayList<>();
        List<CarInfo> updateList = new ArrayList<>();

        int sheetCount = book.getNumberOfSheets();//获取sheet数量
        try{
            for(int i=0;i<sheetCount;i++){
                Sheet sheet = book.getSheetAt(i);
                int beginIndex = 2;//数据入库开始行
                int countRow = sheet.getLastRowNum() + 1;//循环的最大行数
                for(int j=beginIndex;j<countRow;j++){
                    Row row = sheet.getRow(j);
                    Object val1 = this.getCellFormatValue(row.getCell(0));//车牌
                    Object val2 = this.getCellFormatValue(row.getCell(1));//车主
                    Object val3 = this.getCellFormatValue(row.getCell(2));//电话
                    Object val4 = this.getCellFormatValue(row.getCell(3));//楼栋
                    Object val5 = this.getCellFormatValue(row.getCell(4));//房号
                    Object val6 = this.getCellFormatValue(row.getCell(5));//备注

                    if(StringUtils.isEmpty(val1) && StringUtils.isEmpty(val2))continue;//车牌和车主为空忽略
                    String key = val1.toString() + val2.toString();//拿车牌+车主作为key判断数据库是否存在
                    boolean exists = dbMap.containsKey(key);

                    CarInfo car = new CarInfo();
                    car.setCarNumber(val1.toString().toUpperCase());
                    if(!StringUtils.isEmpty(val2)){
                        String str2 = val2.toString().replaceAll("[^\\-0-9a-zA-Z\u4E00-\u9FA5]",",");
                        car.setOwner(str2);
                    }
                    if(!StringUtils.isEmpty(val3)){
                        car.setPhone(val3.toString());
                    }
                    if(!StringUtils.isEmpty(val4)){
                        car.setBuilding(val4.toString());
                    }
                    if(!StringUtils.isEmpty(val5)){
                        car.setHouse(val5.toString());
                    }
                    if(!StringUtils.isEmpty(val6)){
                        car.setRemarks(val6.toString());
                    }
                    if(exists){
                        CarInfo info = dbMap.get(key);
                        car.setId(info.getId());
                        updateList.add(car);
                    }else{
                        insertList.add(car);
                    }
                }
            }
            carInfoService.batchAdd(insertList);
            carInfoService.batchUpdate(updateList);
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

    @RequestMapping("find")
    @ResponseBody
    public Object find(HttpServletRequest request){
        PageInfo page = getPage(request);
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(super.getParam("searchType")) && !StringUtils.isEmpty(super.getParam("searchType"))){
            criteria.put("searchType",super.getIntegerParam("searchType"));
            if(super.getIntegerParam("searchType") == 0){//车牌统一转成大写
                criteria.put("searchKey",super.getParam("searchKey").toUpperCase());
            }else{
                criteria.put("searchKey",super.getParam("searchKey"));
            }
        }

        PageInfo<ResidentInfo> pageResult = carInfoService.findPage(page,criteria,isDownloadReq());
        return this.getGridData(pageResult);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("keke/car/add");
        return mv;
    }

    @ResponseBody
    @RequestMapping("add")
    public Object add(CarInfo info){
        List<CarInfo> dbList = carInfoService.findAll();
        Map<String,CarInfo> dbMap = dbList.stream().collect(Collectors.toMap(k->{
            return k.getCarNumber() + k.getOwner();
        },part -> part));
        if(dbMap.containsKey(info.getCarNumber() + info.getOwner()))return new ResultResp<>("楼栋房号已存在");
        if(carInfoService.add(info)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(Long id){
        ModelAndView mv = new ModelAndView("keke/car/update");
        CarInfo car = carInfoService.findById(id);
        mv.addObject("car",car);
        return mv;
    }

    @ResponseBody
    @RequestMapping("update")
    public Object update(CarInfo info){
        List<CarInfo> dbList = carInfoService.findAll();
        Map<String,CarInfo> dbMap = dbList.stream().collect(Collectors.toMap(k->{
            return k.getCarNumber() + k.getOwner();
        },part -> part));
        if(dbMap.containsKey(info.getCarNumber() + info.getOwner())){
            CarInfo dbCar = dbMap.get(info.getCarNumber() + info.getOwner());
            if(dbCar.getId().equals(info.getId())){
                if(carInfoService.update(info)){
                    return ResultResp.ok();
                }else{
                    return ResultResp.fail();
                }
            }else{
                return new ResultResp<>("车辆信息已存在");
            }
        }else{
            if(carInfoService.update(info)){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id){
        if(carInfoService.delete(id)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
