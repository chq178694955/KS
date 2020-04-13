package com.king.framework.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.king.framework.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public class BaseController {

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }

    protected boolean isDownloadReq() {
        String isDownload = this.getRequest().getParameter("isDownload");
        return isDownload != null;
    }

    protected String getParam(String paramName) {
        String[] parameters = this.getRequest().getParameterValues(paramName);
        if (parameters != null && parameters.length != 0) {
            if (parameters.length == 1) {
                return parameters[0];
            } else {
                StringBuffer parameter = new StringBuffer();
                int len = parameters.length;

                for(int i = 0; i < len; ++i) {
                    parameter.append(parameters[i]);
                    if (i < len - 1) {
                        parameter.append(",");
                    }
                }

                return parameter.toString();
            }
        } else {
            return null;
        }
    }

    protected Integer getIntegerParam(String paramName) {
        String paramValue = this.getParam(paramName);
        return StringUtils.isEmpty(paramValue) ? null : Integer.parseInt(paramValue);
    }

    protected Long getLongParam(String paramName) {
        String paramValue = this.getParam(paramName);
        return StringUtils.isEmpty(paramValue) ? null : Long.parseLong(paramValue);
    }

    protected Double getDoubleParam(String paramName) {
        String paramValue = this.getParam(paramName);
        return StringUtils.isEmpty(paramValue) ? null : Double.parseDouble(paramValue);
    }

    protected Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap();

        String paramName;
        String paramValue;
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            paramValue = JSONArray.toJSONString(paramValues);
            if (paramValues.length == 1) {
                paramValue = paramValues[0];
            }
            params.put(paramName,paramValue);
        }

        return params;
    }

    protected <T> PageInfo<T> getPage(HttpServletRequest request) {
        Integer pageNo = this.getIntegerParam("page");
        pageNo = pageNo == null ? 1 : pageNo;
        Integer pageSize = this.getIntegerParam("limit");
        pageSize = pageSize == null ? 20 : pageSize;
        PageInfo<T> page = new PageInfo();
        page.setPageNum(pageNo);
        page.setPageSize(pageSize);
        return page;
    }

    protected <T> Map<String, Object> getGridData(PageInfo<T> page) {
        Map<String, Object> map = new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("data", page.getList());
        map.put("count", page.getTotal());
        if (page.getTotal() == -1L) {
            int total = (page.getPageNum() - 1) * page.getPageSize() + (page.getList() != null ? page.getList().size() : 0);
            if (page.getList() != null && page.getList().size() == page.getPageSize()) {
                ++total;
            }

            map.put("total", total);
        }
        return map;
    }

    @Value("${i18n.resources-locations}")
    private String resourceLocations;

    protected JSONArray loadI18nData() {
        Locale locale = Locale.getDefault();
        Resource[] resources = I18nUtils.loadResources(resourceLocations);
        JSONArray result = new JSONArray();
        Map<Object,Object> dataMap = new HashMap<>();
        for(Resource res : resources){
            String filename = res.getFilename();
            if(filename.contains(locale.getLanguage() + "_" + locale.getCountry())){
                Properties props = null;
                try {
                    props = PropertiesLoaderUtils.loadProperties(res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dataMap = props;
                for(Map.Entry<Object,Object> item : dataMap.entrySet()){
                    JSONObject data = new JSONObject();
                    data.put("key",item.getKey());
                    data.put("val",item.getValue());
                    result.add(data);
                }
            }
        }
        return result;
    }

}
