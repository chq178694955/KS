package com.king.keke.controller;

import com.github.pagehelper.PageInfo;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.model.ResultResp;
import com.king.framework.utils.DateUtils;
import com.king.keke.entity.ResidentInfo;
import com.king.keke.service.IResidentInfoService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @创建人 chq
 * @创建时间 2020/4/20
 * @描述
 */
@Controller
@RequestMapping("keke/resident")
public class ResidentInfoController extends BaseController {

    @Autowired
    private IResidentInfoService residentInfoService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("keke/resident/main");
        return mv;
    }

    @RequestMapping("find")
    @ResponseBody
    public Object find(HttpServletRequest request){
        PageInfo page = getPage(request);
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(super.getParam("searchType")) && !StringUtils.isEmpty(super.getParam("searchType"))){
            criteria.put("searchType",super.getIntegerParam("searchType"));
            criteria.put("searchKey",super.getParam("searchKey"));
        }

        PageInfo<ResidentInfo> pageResult = residentInfoService.findPage(page,criteria,isDownloadReq());
        return this.getGridData(pageResult);
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

        List<ResidentInfo> dbList = residentInfoService.findAll();
        Map<String,ResidentInfo> dbMap = dbList.stream().collect(Collectors.toMap(k->{
            return k.getBuilding() + k.getHouseNo();
        },part -> part));

        List<ResidentInfo> insertList = new ArrayList<>();
        List<ResidentInfo> updateList = new ArrayList<>();

        int sheetCount = book.getNumberOfSheets();//获取sheet数量
        try{
            for(int i=0;i<sheetCount;i++){
                Sheet sheet = book.getSheetAt(i);
                int beginIndex = 3;//数据入库开始行
                int countRow = sheet.getLastRowNum() + 1;//循环的最大行数
                for(int j=beginIndex;j<countRow;j++){
                    Row row = sheet.getRow(j);
                    Object val1 = this.getCellFormatValue(row.getCell(1));//房号
                    Object val2 = this.getCellFormatValue(row.getCell(2));//姓名
                    BigDecimal val3 = this.parseDecimal(this.getCellFormatValue(row.getCell(3)));//面积
                    Object val4 = this.getCellFormatValue(row.getCell(4));//身份证
                    Object val5 = this.getCellFormatValue(row.getCell(5));//电话

                    if(StringUtils.isEmpty(val1))continue;//房号为空的数据直接忽略
                    String key = ((i + 1) + "") + val1.toString();//拿楼栋+房号作为key判断数据库是否存在
                    boolean exists = dbMap.containsKey(key);

                    ResidentInfo resident = new ResidentInfo();
                    resident.setBuilding((i + 1)+"");//楼栋
                    resident.setHouseNo(val1.toString());
                    if(!StringUtils.isEmpty(val2)){
                        String str2 = val2.toString().replaceAll("[^0-9a-zA-Z\u4E00-\u9FA5]",",");
                        resident.setHouseHolder(str2);
                    }
                    resident.setArea(val3);
                    if(!StringUtils.isEmpty(val4)){
                        String str4= val4.toString().toUpperCase().replaceAll("[^0-9a-zA-Z]",",");//将特殊符号全部转换成逗号分隔
                        resident.setIdCardNo(str4);
                    }
                    if(!StringUtils.isEmpty(val5)){
                        String str5 = val5.toString().toUpperCase().replaceAll("[^0-9a-zA-Z\\-]",",");//将特殊符号全部转换成逗号分隔
                        resident.setPhone(str5);
                    }
                    resident.setRemarks(StringUtils.isEmpty(val2) ? "空户" : null);
                    if(exists){
                        ResidentInfo info = dbMap.get(key);
                        resident.setId(info.getId());
                        updateList.add(resident);
                    }else{
                        insertList.add(resident);
                    }
                }
            }
            residentInfoService.batchAdd(insertList);
            residentInfoService.batchModify(updateList);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResultResp<>("导入Excel文件异常！");
        }
        return ResultResp.ok();
    }

    private BigDecimal parseDecimal(Object obj){
        if(obj == null)return null;
        return new BigDecimal(obj.toString());
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

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("keke/resident/add");
        return mv;
    }

    @ResponseBody
    @RequestMapping("add")
    public Object add(ResidentInfo info){
        ResidentInfo dbinfo = residentInfoService.findByBuildAndHouse(info);
        if(dbinfo != null)return new ResultResp<>("楼栋房号已存在");
        if(residentInfoService.add(info)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(Long id){
        ModelAndView mv = new ModelAndView("keke/resident/update");
        ResidentInfo resident = residentInfoService.findById(id);
        mv.addObject("resident",resident);
        return mv;
    }

    @ResponseBody
    @RequestMapping("update")
    public Object update(ResidentInfo info){
        ResidentInfo dbinfo = residentInfoService.findByBuildAndHouse(info);
        if(dbinfo != null && !dbinfo.getId().equals(info.getId()))return new ResultResp<>("楼栋房号已存在");
        if(residentInfoService.modify(info)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @ResponseBody
    @RequestMapping("delete")
    public Object delete(Long id){
        if(residentInfoService.del(id)){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
