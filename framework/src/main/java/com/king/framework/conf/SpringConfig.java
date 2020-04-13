package com.king.framework.conf;

import com.king.framework.base.BaseDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * spring的一些配置，这里可以沿用XML方式进行配置，也可以用注解
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
@Configuration
@ImportResource(locations = {
        "classpath:spring-ehcache.xml"
})
public class SpringConfig {

//    /**
//     * 档案缓存工厂类
//     * @return
//     */
//    @Bean
//    public EhCacheManagerFactoryBean archiveEhcacheManagerFactory(){
//        EhCacheManagerFactoryBean archiveEhcacheManagerFactory = new EhCacheManagerFactoryBean();
//        Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:");
//        archiveEhcacheManagerFactory.setConfigLocation(resource);
//        return archiveEhcacheManagerFactory;
//    }


}
