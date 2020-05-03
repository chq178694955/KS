package com.king.framework.echarts.component;

/**
 * @创建人 chq
 * @创建时间 2020/5/2
 * @描述
 */
public class Legend<T> extends Component {

    private String orient;

    private String x;

    private String y;

    private String formatter;

    private T data;

    public String getOrient() {
        return orient;
    }

    public Legend setOrient(String orient) {
        this.orient = orient;
        return this;
    }

    public String getX() {
        return x;
    }

    public Legend setX(String x) {
        this.x = x;
        return this;
    }

    public String getY() {
        return y;
    }

    public Legend setY(String y) {
        this.y = y;
        return this;
    }

    public String getFormatter() {
        return formatter;
    }

    public Legend<T> setFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public T getData() {
        return data;
    }

    public Legend<T> setData(T data) {
        this.data = data;
        return this;
    }
}
