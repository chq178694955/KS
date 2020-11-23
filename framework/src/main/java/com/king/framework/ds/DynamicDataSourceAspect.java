package com.king.framework.ds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
@Component
@Order(1)// 保证该AOP在@Transactional之前执行
@Aspect
public class DynamicDataSourceAspect {

    @Before("execution(* com.king.*.service..*.*(..))")
    public void before(JoinPoint point){
        try {
            DataSourceSign annotationOfClass =
                    point.getTarget().getClass().getAnnotation(DataSourceSign.class);

            String methodName = point.getSignature().getName();

            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();

            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);

            DataSourceSign methodAnnotation = method.getAnnotation(DataSourceSign.class);

            methodAnnotation = methodAnnotation == null ? annotationOfClass : methodAnnotation;

            DataSourceType dataSourceType = methodAnnotation != null &&  methodAnnotation.value() !=null ?
                    methodAnnotation.value() : DataSourceType.MASTER ;
            DataSourceContextHolder.setDataSource(dataSourceType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.king.*.service..*.*(..))")
    public void after(JoinPoint point){
        DataSourceContextHolder.removeDataSource();
    }

}
