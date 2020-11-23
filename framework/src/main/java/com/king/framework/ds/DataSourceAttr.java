package com.king.framework.ds;

import java.io.Serializable;

/**
 * 数据源属性
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
public class DataSourceAttr implements Serializable {
    private static final long serialVersionUID = 3623284728877870199L;
    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    private int initialSize;
    private int maxActive;
    private int minIdle;

    public String getName() {
        return name;
    }

    public DataSourceAttr setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DataSourceAttr setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceAttr setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceAttr setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public DataSourceAttr setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public DataSourceAttr setInitialSize(int initialSize) {
        this.initialSize = initialSize;
        return this;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public DataSourceAttr setMaxActive(int maxActive) {
        this.maxActive = maxActive;
        return this;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public DataSourceAttr setMinIdle(int minIdle) {
        this.minIdle = minIdle;
        return this;
    }
}
