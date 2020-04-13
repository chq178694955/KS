package com.king.app.webapp.conf;

import com.king.app.webapp.filter.ParamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 * @创建人 chq
 * @创建时间 2020/4/10
 * @描述
 */
@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean registrationBean(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new ParamFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }

}
