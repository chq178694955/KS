package com.king.framework.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用本地线程切换数据源
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
public class DataSourceContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSource(DataSourceType type){
        contextHolder.set(type);
    }

    public static DataSourceType getDataSource(){
        return contextHolder.get();
    }

    public static void removeDataSource(){
        contextHolder.remove();
    }

}
