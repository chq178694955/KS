package com.king.framework.export;

import com.king.framework.exception.BusinessException;
import com.king.framework.utils.CacheUtils;
import com.king.framework.utils.FileUtils;
import com.king.framework.utils.I18nUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class HtmlDataGridExport<T> extends HssfTableExport<T> implements ITableExport<T>{

    private HttpServletResponse response;
    private HttpServletRequest request;
    private ServletOutputStream out;

    public HtmlDataGridExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.init(request,response);
    }

    private void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        this.out = response.getOutputStream();
    }

    @Override
    protected Object doExport(String author, ExcelTable excelTable) throws IOException {
        Map<String,String> dataMap = null;
        try {
            dataMap = this.createXlsFile(author, excelTable);
            CacheUtils.put("grid_export_cache", dataMap.get("guid"), dataMap, (long)this.getLimitMinutes(), TimeUnit.MINUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap.get("guid");
    }

    private Map<String,String> createXlsFile(String creator, ExcelTable etable) throws IOException {
        Map<String,String> dataMap = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        String title = etable.getSheetName();
        FileOutputStream fos = null;
        FileInputStream fis = null;

        try {
            HSSFWorkbook wb = super.exportToExcel(creator, etable);
            String filePath = this.getFilePath(uuid,".xls");
            File xlsFile = FileUtils.createFile(filePath);
            fos = new FileOutputStream(xlsFile);
            wb.write(fos);
        } finally {
            if(fis != null){
                fis.close();
            }
            if(fos != null){
                fos.close();
            }
        }
        dataMap.put("title",title);
        dataMap.put("guid",uuid);
        dataMap.put("suffix",".xls");
        return dataMap;
    }

    private String getFilePath(String guid,String suffix) {
        String fileName = guid + suffix;
        String dirPath = this.getExportDirPath();
        File path = new File(dirPath);
        if (!path.exists()) {
            FileUtils.createDir(dirPath);
        }

        return dirPath + (dirPath.endsWith(File.separator) ? fileName : File.separator + fileName);
    }

    protected String getExportDirPath() {
        //return ConfigUtils.getProperty("default.export.dir", ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/tmp"));
        return request.getServletContext().getRealPath("/");
    }

    protected int getLimitMinutes() {
//        return ConfigUtils.getIntProperty("default.export.limitMinutes", Constants.DEFAULT_EXPORT_LIMITMINUTES_VALUE);
        return 15;
    }

    public void download(String guid) throws BusinessException {
        Map<String,String> data = (Map<String,String>)CacheUtils.get("grid_export_cache", guid);
        if (data == null) {
            throw new BusinessException(I18nUtils.get("com.excel.download.fail"));
        } else {
            String filePath = this.getFilePath(data.get("guid"),data.get("suffix"));
            String fileName = data.get("title") + data.get("suffix");
            try {
                this.response.setCharacterEncoding("UTF-8");
                this.response.addHeader("Content-Disposition", "attachment;filename=".concat(new String(fileName.getBytes("GB2312"), "ISO-8859-1")));
                this.response.setHeader("Connection", "close");
                this.response.setHeader("Content-Type", "application/vnd.ms-excel");

                InputStream in = new FileInputStream(filePath);

                byte[] buffer = new byte[1024];
                ServletOutputStream out = this.response.getOutputStream();

                int len;
                while((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }

                out.close();
                in.close();
            } catch (Exception var17) {
                throw new BusinessException("HtmlDataGridExport.download.exception:", var17.getCause());
            } finally {
                CacheUtils.remove("grid_export_cache", guid);
            }
        }
    }

}
