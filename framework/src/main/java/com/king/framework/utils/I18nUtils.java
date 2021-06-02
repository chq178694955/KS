package com.king.framework.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

/**
 * @创建人 chq
 * @创建时间 2019/12/3
 * @描述
 */
@Component
public class I18nUtils {

    @Value("${i18n.default.language}")
    private static String LANGUAGE;
    @Value("${i18n.default.country}")
    private static String COUNTRY;

    private static Locale locale;

    private static MessageSource messageSource;

    public I18nUtils(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public static String get(String msgKey,Object ...args){
        try{
            return messageSource.getMessage(msgKey,args, getLocale());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return msgKey;
    }

    public static Locale getLocale(){
        return LocaleContextHolder.getLocale();
    }

    /**
     * 加载资源文件数组
     * @param basename
     * @return
     */
    public static Resource[] loadResources(String basename){
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(basename);
            return resources;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
