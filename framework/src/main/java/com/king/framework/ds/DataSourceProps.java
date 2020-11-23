package com.king.framework.ds;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
@Configuration
@ConfigurationProperties(prefix = "king")
@PropertySource("classpath:datasources.properties")
public class DataSourceProps {

    private String defaultDs;

    private List<DataSourceAttr> attrs =new ArrayList<>();

    @PostConstruct
    public void init(){
        if(!CollectionUtils.isEmpty(attrs)){
            for(DataSourceAttr attr : attrs){
                if(StringUtils.isEmpty(attr.getName()))continue;
            }
        }
    }

    public String getDefaultDs() {
        return defaultDs;
    }

    public void setDefaultDs(String defaultDs) {
        this.defaultDs = defaultDs;
    }

    public List<DataSourceAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<DataSourceAttr> attrs) {
        this.attrs = attrs;
    }
}
