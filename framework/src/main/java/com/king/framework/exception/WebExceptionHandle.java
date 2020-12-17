package com.king.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebExceptionHandle extends SimpleMappingExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(WebExceptionHandle.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        if(logger.isErrorEnabled()){
            e.printStackTrace();
            logger.error("发生异常",e);
        }
        String viewName = super.determineViewName(e,request);
        if(e instanceof HttpMessageNotReadableException){
            super.addStatusCode(viewName,HttpStatus.BAD_REQUEST.value());
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            super.addStatusCode(viewName,HttpStatus.METHOD_NOT_ALLOWED.value());
        }else if(e instanceof HttpMediaTypeNotSupportedException){
            super.addStatusCode(viewName,HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        }else if(e instanceof Exception){
            if(e instanceof BusinessException){
                super.addStatusCode(viewName,10001);
            }else if(e instanceof CacheException){
                super.addStatusCode(viewName,10002);
            }else if(e instanceof DataException){
                super.addStatusCode(viewName,10003);
            }else if(e instanceof ParamException){
                super.addStatusCode(viewName,10004);
            }else{
                super.addStatusCode(viewName,HttpStatus.INTERNAL_SERVER_ERROR.value());
            }

        }
        return super.doResolveException(request, response, o, e);
    }

}
