package com.king.app.webapp.filter;


import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 参数过滤器
 * @创建人 chq
 * @创建时间 2020/4/10
 * @描述
 */
public class ParamFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ParamFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("参数过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String menuId = request.getParameter("menuId");
        if(StringUtil.isNotEmpty(menuId)){
            request.setAttribute("menuId",menuId);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
