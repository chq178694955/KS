package com.king.framework.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源切换
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(){}

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType type = DataSourceContextHolder.getDataSource();
        if(type == null){
            return DataSourceType.MASTER;
        }
        return type;
    }
}
