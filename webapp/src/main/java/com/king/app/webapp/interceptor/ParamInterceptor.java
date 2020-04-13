package com.king.app.webapp.interceptor;

import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参数拦截器
 * @创建人 chq
 * @创建时间 2020/4/10
 * @描述
 */
public class ParamInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ParamInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String menuId = request.getParameter("menuId");
        if(StringUtil.isNotEmpty(menuId)){
            request.setAttribute("menuId",menuId);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
