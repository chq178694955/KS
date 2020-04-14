package com.king.app.webapp.controller.system;

import com.king.framework.exception.BusinessException;
import com.king.framework.export.HtmlDataGridExport;
import com.king.framework.utils.I18nUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Controller
@RequestMapping("global/datagrid")
public class DataGridController {

    public static final String PARAM_RSP_SUCCESS = "success";
    public static final String PARAM_RSP_MESSAGE = "message";

    public DataGridController() {
    }

    @RequestMapping({"download"})
    public void download(HttpServletRequest request, HttpServletResponse response, String guid) {
        HtmlDataGridExport exporter = null;
        try {
            exporter = new HtmlDataGridExport(request, response);
            exporter.download(guid);
        } catch (Exception var6) {
            throw new BusinessException(I18nUtils.get("com.excel.download.fail"));
        }
    }
}
