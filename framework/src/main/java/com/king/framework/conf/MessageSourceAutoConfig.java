package com.king.framework.conf;

import com.king.framework.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 国家化资源文件自动装配
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
@Configuration
public class MessageSourceAutoConfig implements WebMvcConfigurer {

    @Value("${i18n.basename}")
    private String basename = "i18n";

    @Value("${i18n.encoding}")
    private String encoding;

    @Value("${i18n.cacheSeconds}")
    private int cacheSeconds = -1;

    @Value("${i18n.alwaysUseMessageFormat}")
    private boolean alwaysUseMessageFormat = false;

    @Value("${i18n.fallbackToSystemLocale}")
    private boolean fallbackToSystemLocale = true;

    @Value("${i18n.resources-locations}")
    private String resourceLocations;

    @Value("${i18n.default.language}")
    private static String language;

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("localeCookie");
        //设置默认区域
        localeResolver.setDefaultLocale(resolverLanguage(language));
        localeResolver.setCookieMaxAge(3600);//设置cookie有效期.
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    private Locale resolverLanguage(String lang){
        Locale locale;
        if(Locale.ENGLISH.getLanguage().equals(lang)){
            locale = new Locale(Locale.US.getLanguage(),Locale.US.getCountry());
        }else if(Locale.SIMPLIFIED_CHINESE.getLanguage().equals(lang)){
            locale = new Locale(Locale.SIMPLIFIED_CHINESE.getLanguage(),Locale.SIMPLIFIED_CHINESE.getCountry());
        }else{
            locale = new Locale(Locale.SIMPLIFIED_CHINESE.getLanguage(),Locale.SIMPLIFIED_CHINESE.getCountry());
        }
        return locale;
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        Resource[] resources = I18nUtils.loadResources(resourceLocations);
        String i18nFiles = "";
        List<String> tempList = new ArrayList<>();
        for(Resource resource : resources){
            String prefix = resource.getFilename().split("_")[0];
            if(!tempList.contains(prefix)){
                tempList.add(prefix);
                i18nFiles = i18nFiles + basename + "/" + prefix + ",";
            }
        }
        if(StringUtils.hasText(i18nFiles)){
            i18nFiles = i18nFiles.substring(0,i18nFiles.length() - 1);
        }
        if(StringUtils.hasText(i18nFiles)){
            messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(i18nFiles)));
        }
        if(encoding != null){
            messageSource.setDefaultEncoding(encoding);
        }
        messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
        messageSource.setCacheSeconds(this.cacheSeconds);
        messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);

        return messageSource;
    }

}
