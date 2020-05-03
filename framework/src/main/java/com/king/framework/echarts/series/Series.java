package com.king.framework.echarts.series;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public abstract class Series<T> {

    private String type;

    private String name;

    private T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
