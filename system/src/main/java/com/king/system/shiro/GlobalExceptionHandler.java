package com.king.system.shiro;

import com.alibaba.fastjson.JSONObject;
import com.king.framework.exception.BusinessException;
import com.king.framework.exception.CacheException;
import com.king.framework.exception.DataException;
import com.king.framework.exception.ParamException;
import com.king.framework.utils.I18nUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 统一异常处理
 * @创建人 chq
 * @创建时间 2020/3/29
 * @描述
 */
@CrossOrigin
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JSONObject handleException(Exception e, HttpServletRequest request, HttpServletResponse response)throws Exception{
        String parameters = "";
        for(String key:request.getParameterMap().keySet()){
            parameters += (key+"="+request.getParameter(key)+",");
        }
        if(parameters.length()>0){
            parameters = parameters.substring(0, parameters.length()-1);
        }
        if(e instanceof SQLException){
            logger.error("*****数据库异常*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(10, I18nUtils.get("ks.global.exception.sql"));
        }else if(e instanceof BusinessException){
            logger.warn("*****业务异常*****" + e.getMessage());
            logger.warn("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(20,I18nUtils.get("ks.global.exception.business"));
        }else if(e instanceof CacheException){
            logger.error("*****缓存异常*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(30,I18nUtils.get("ks.global.exception.cache"));
        }else if(e instanceof DataException){
            logger.warn("*****数据异常*****" + e.getMessage());
            logger.warn("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(40,I18nUtils.get("ks.global.exception.data"));
        }else if(e instanceof ParamException){
            logger.error("*****参数异常*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(50,I18nUtils.get("ks.global.exception.param"));
        }else if(e instanceof UnknownAccountException){
            logger.error("*****账号没有找到*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(60,I18nUtils.get("ks.global.exception.noAccount"));
        }else if(e instanceof IncorrectCredentialsException){
            logger.error("*****用户名或密码错误*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(70,I18nUtils.get("ks.global.exception.accountOrPasswordErr"));
        }else if(e instanceof ShiroException){
            logger.error("*****鉴权或授权过程出错*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(80,I18nUtils.get("ks.global.exception.authcErr"));
        }else if(e instanceof UnauthorizedException){
            logger.error("*****用户没有访问权限*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            return getResult(90,I18nUtils.get("ks.global.exception.noAuth"));
        }else{
            logger.error("*****服务器异常*****" + e.getMessage());
            logger.error("RequestFail["+request.getRequestURI()+"]["+parameters+"]");
            e.printStackTrace();
            return getResult(500,I18nUtils.get("ks.global.exception.server"));
        }
    }

    private JSONObject getResult(Integer code,String msg){
        JSONObject result = new JSONObject();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }

}
