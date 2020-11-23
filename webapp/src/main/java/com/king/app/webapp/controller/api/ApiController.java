package com.king.app.webapp.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
@Controller
@RequestMapping("api")
public class ApiController extends BaseController {

    @Autowired
    private Environment env;

    /**
     * 第三方应用登录并生成token
     * @param username
     * @param appKey
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public Object login(String username,String appKey){
        Map<String,Object> map = new HashMap<>();
        map.put("token", JwtUtil.generateToken(username + appKey));
        return new ResultResp(map);
    }

    @RequestMapping("test")
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response){
        JSONObject object = new JSONObject();
        object.put("name","Hello World");
        String path = env.getProperty("KS_WEB_CONFIG");
        System.out.println(path);
        String address = path + File.separator + "test" + File.separator + "keke_logo.png";
        super.downloadFile("keke_logo.png",address,response);
    }

    @RequestMapping("excel")
    @ResponseBody
    public void excel(HttpServletRequest request, HttpServletResponse response){
        try {
            File cfgFile = ResourceUtils.getFile("classpath:excel_template.xlsx");
            super.downloadFile("excel模板.xlsx", cfgFile.getPath(),response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
