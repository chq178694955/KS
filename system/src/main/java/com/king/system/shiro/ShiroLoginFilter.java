package com.king.system.shiro;

import com.king.framework.model.UserInfo;
import com.king.framework.utils.AuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroLoginFilter extends AccessControlFilter {

    public boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"));
    }

    public void respWriter(ServletResponse servletResponse){
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        //在响应头设置session状态
        response.setHeader("session-status", "timeout");
        response.setStatus(999);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        if(isLoginRequest(request,response)){
            return Boolean.TRUE;
        }
        if(isAjax(request)){
            respWriter(response);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        saveRequestAndRedirectToLogin(servletRequest,servletResponse);
        return false;
    }
}
