package com.king.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
@Component
public class BeanLoader implements ApplicationContextAware {
    private static ApplicationContext context;

    public BeanLoader() {
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        context = context;
    }

    public static Object getBean(String beanId) {
        return context.containsBean(beanId) ? context.getBean(beanId) : null;
    }
}
