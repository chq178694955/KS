package com.king.framework.export;

import com.github.pagehelper.StringUtil;
import com.king.framework.utils.DateUtils;
import com.king.framework.utils.ReflectUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class HssfTableExport<T> extends AbsTableExport<T> implements ITableExport<T> {

    @Override
    protected Object doExport(String author, ExcelTable excelTable) throws IOException {
        return this.exportToExcel(author,excelTable);
    }

    protected HSSFWorkbook exportToExcel(String creator, ExcelTable etable) {
        HSSFWorkbook wb = new HSSFWorkbook();

        String sheetTitle = etable.getSheetName();
        sheetTitle = sheetTitle != null && !sheetTitle.equals("") ? sheetTitle : "sheet";
        etable.setSheetName(sheetTitle);
        wb = this.writeSheet(wb, creator, etable);

        return wb;
    }

    private HSSFWorkbook writeSheet(HSSFWorkbook wb, String creator, ExcelTable etable) {
        String title = etable.getSheetName();
        ExcelHeader header = etable.getHeader();
        ExcelBody body = etable.getBody();
        String createTime = DateUtils.format(new Date().getTime(), "yyyy-MM-dd HH:mm:ss");
        HSSFSheet sheet = wb.createSheet(title);
        sheet.setDisplayGridlines(false);

        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(wb);// 获取列头样式对象
        HSSFCellStyle style = this.getStyle(wb);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, header.getMaxCols() - 1));
        cell.setCellStyle(columnTopStyle);
        cell.setCellValue(new HSSFRichTextString(title));

        int startRow = 2;//设定起始行数
        for(int i=0;i<header.getHeaders().size();i++){
            List<Field> fields = header.getHeaders().get(i);
            HSSFRow headerRow = sheet.createRow(startRow + i);
            int fieldIndex = 0;//列索引
            for(Field f : fields){
                HSSFCell headerCell = headerRow.createCell(fieldIndex);
                headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.addMergedRegion(new CellRangeAddress(startRow + i, startRow + i, fieldIndex, (fieldIndex + f.getColspan() - 1)));
                HSSFRichTextString text = new HSSFRichTextString(f.getText() == null ? StringUtils.EMPTY_STRING : f.getText());
                headerCell.setCellValue(text);
                headerCell.setCellStyle(style);
                fieldIndex = fieldIndex + f.getColspan();
            }
        }

        startRow = startRow + header.getHeaders().size();
        List<Field> realFields = header.getFields();
        List<?> bodies = body.getDatas();
        if (bodies != null && ! bodies.isEmpty()) {
            boolean isArrayData = bodies.get(0).getClass().isArray();
            if (isArrayData) {
                this.initArrayDatas(bodies);//数组方式，待实现
            } else {
                this.initBeanDatas(wb, sheet,realFields,bodies,startRow);//javaBean
            }
        }

        sheet.setGridsPrinted(true);
        return wb;
    }

    /**
     * 数组方式
     * @param list
     */
    private void initArrayDatas(List<?> list) {
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Object data = var4.next();
            Object[] aa = (Object[])data;
        }

    }

    /**
     * javaBean方式
     * @param sheet
     * @param fields
     * @param list
     * @param startRow
     */
    private void initBeanDatas(HSSFWorkbook wb, HSSFSheet sheet, List<Field> fields,List<?> list, int startRow) {
        this._initBeanDatas(wb, sheet, fields,list, startRow);
    }

    private Integer _initBeanDatas(HSSFWorkbook wb, HSSFSheet sheet, List<Field> fields, List<?> list, Integer startRow) {
        HSSFCellStyle leftStyle = this.getLeftStyle(wb);
        HSSFCellStyle rightStyle = this.getRightStyle(wb);
        HSSFCellStyle style = this.getStyle(wb);
        for(int i = 0; i < list.size(); ++i) {
            Object obj = list.get(i);

            Map<String, Object> map = obj instanceof Map ? (Map)obj : object2Map(obj);
            HSSFRow row = sheet.createRow(startRow);
            int fieldIndex = 0;//列索引
            for(Field f : fields){

                HSSFCell cell = row.createCell(fieldIndex);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                sheet.addMergedRegion(new CellRangeAddress(startRow, startRow, fieldIndex, (fieldIndex + f.getColspan() - 1)));
                if(map.containsKey(f.getName())){
                    Object value = map.get(f.getName());
                    HSSFRichTextString text = new HSSFRichTextString(value == null ? StringUtils.EMPTY_STRING : value.toString());
                    cell.setCellValue(text);
                }else{
                    HSSFRichTextString text = new HSSFRichTextString(StringUtils.EMPTY_STRING);
                    cell.setCellValue(text);
                }
                if(f.getAlign().equals("left")){
                    cell.setCellStyle(leftStyle);
                }else if(f.getAlign().equals("right")){
                    cell.setCellStyle(rightStyle);
                }else{
                    cell.setCellStyle(style);
                }

                fieldIndex = fieldIndex + f.getColspan();
            }
            try {
                Object children = ReflectUtils.getValueByFieldName(list.get(i), "children");
                if (children != null && children instanceof List) {
                    startRow = this._initBeanDatas(wb, sheet, fields,(List)children, startRow);
                }
            } catch (Exception var8) {
            }
            startRow = startRow + 1;
        }

        return startRow;
    }

    private Map<String,Object> object2Map(Object obj){
        Map<String,Object> map = new HashMap<>();
        if(obj == null)return map;
        Class clazz = obj.getClass();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        try{
            for(java.lang.reflect.Field f : fields){
                f.setAccessible(true);
                map.put(f.getName(),f.get(obj));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return map;
    }

    /*
     * 列头单元格样式
     */
    private HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    private HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    private HSSFCellStyle getLeftStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    private HSSFCellStyle getRightStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }
}
