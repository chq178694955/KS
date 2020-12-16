package com.king.app.webapp.filter;


import com.alibaba.fastjson.JSONObject;
import com.king.framework.model.ResultResp;
import com.king.framework.model.TokenStatus;
import com.king.framework.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 接口过滤器
 * @创建人 chq
 * @创建时间 2020/4/10
 * @描述
 */
public class ApiFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ApiFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //白名单放行
        String uri = request.getRequestURI();
        if(uri.contains("api/login")){
            filterChain.doFilter(servletRequest,servletResponse);//直接放行
            return ;
        }

        String token = request.getHeader("token");
        TokenStatus tokenStatus = JwtUtil.verifyToken(token);
        switch (tokenStatus){
            case VALID:
                String user = JwtUtil.getUserNameFromToken(token);
                filterChain.doFilter(servletRequest,servletResponse);
                break;
            case INVALID:
                accessDeny(response,new ResultResp(1001,"令牌无效"));
                break;
            case EXPIRED:
                accessDeny(response,new ResultResp(1001,"令牌过期"));
                break;
        }
        */
        filterChain.doFilter(servletRequest,servletResponse);//直接放行
        return ;
    }

    private void accessDeny(HttpServletResponse response, ResultResp result){
        PrintWriter write = null;
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String json = JSONObject.toJSON(result).toString();
        try {
            write = response.getWriter();
            write.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(write != null){
                write.flush();
                write.close();
            }
        }
    }
}
