package com.king.framework.echarts.component;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public class Tooltip extends Component {

    private String trigger;

    private String formatter;

    private AxisPointer axisPointer;

    public String getTrigger() {
        return trigger;
    }

    public Tooltip setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public String getFormatter() {
        return formatter;
    }

    public Tooltip setFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    public AxisPointer getAxisPointer() {
        return axisPointer;
    }

    public Tooltip setAxisPointer(AxisPointer axisPointer) {
        this.axisPointer = axisPointer;
        return this;
    }
}
