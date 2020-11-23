package com.king.framework.ds;

import java.lang.annotation.*;

/**
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSign {
    DataSourceType value() default DataSourceType.MASTER;
}
