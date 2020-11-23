package com.king.framework.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Excel批量导入测试入口
 * @创建人 chq
 * @创建时间 2020/9/4
 * @描述
 */
public class ExcelBatchImportMain {

    /**
     * 用于存放每一批次的数据，每一批次一个线程
     */
    private static Map<String,List<XSSFRow>> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 缓存批次前缀
     */
    private static final String CACHE_KEY_PREFIX = "EXCEL_BATCH_DATA_";

    /**
     * 500条一次执行
     */
    private static final int BATCH_COUNT = 10000;

    public static void main(String[] args) {
        try {
            //创建输入流
            //InputStream stream = new FileInputStream("C:\\Users\\17869\\Desktop\\SIM卡开户信息导入模板20200904.xlsx");
            InputStream stream = new FileInputStream("C:\\Users\\17869\\Desktop\\SIM卡日用量信息导入模板.xlsx");
            //获取Excel文件对象
            XSSFWorkbook book = new XSSFWorkbook(stream);
            System.out.println("总sheet数：" + book.getNumberOfSheets());
            XSSFSheet sheet = book.getSheetAt(0);
            System.out.println("总行数：" + sheet.getLastRowNum());
            /*
            int rows = sheet.getLastRowNum() +1;
            int totalPages;
            int pageNo = 0;
            if(rows % BATCH_COUNT == 0){
                totalPages = rows / BATCH_COUNT;
            }else{
                totalPages = rows / BATCH_COUNT + 1;
            }
            for(;pageNo<totalPages;pageNo++){
                List<XSSFRow> batchRows = new ArrayList<>();
                for(int i=0;i<BATCH_COUNT;i++){
                    if(pageNo * BATCH_COUNT + i <= rows){
                        XSSFRow row = sheet.getRow(pageNo * BATCH_COUNT + i);
                        batchRows.add(row);
                    }
                }
                concurrentHashMap.put(CACHE_KEY_PREFIX + pageNo,batchRows);
                Thread thread = new Thread(new RunnerExcel2Db(pageNo));
                thread.start();
            }
            */

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class RunnerExcel2Db implements Runnable{

        private int batchNo;

        public RunnerExcel2Db(int batchNo){
            this.batchNo = batchNo;
        }

        @Override
        public void run() {
            List<XSSFRow> batchRows = concurrentHashMap.get(CACHE_KEY_PREFIX + this.batchNo);
            System.out.println("当前执行批次：" + this.batchNo + "\t当前批次数据行：" + (batchRows != null ? batchRows.size() : 0));
            for(XSSFRow row : batchRows){
                if(row == null)continue;
                XSSFCell cell = row.getCell(0);
                System.out.println("批次" + this.batchNo + "，数据：" + cell.getStringCellValue());
            }
        }

    }

}
