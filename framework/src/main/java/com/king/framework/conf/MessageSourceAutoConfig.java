package com.king.framework.conf;

import com.king.framework.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 国家化资源文件自动装配
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
@Configuration
public class MessageSourceAutoConfig {

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
