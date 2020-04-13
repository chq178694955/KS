package com.king.app.webapp.starter;

//import org.mybatis.spring.annotation.MapperScan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 微服务启动类
 * @创建人 chq
 * @创建时间 2020/3/16
 * @描述
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.king"})
@EnableTransactionManagement
@MapperScan({"com.king.*.dao","com.king.*.*.dao"})
public class Startup extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return super.configure(builder);
        return builder.sources(Startup.class);
    }
}
