package com.king.framework.echarts.component;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public class Component {

    private boolean show = true;

    public boolean isShow() {
        return show;
    }

    public Component setShow(boolean show) {
        this.show = show;
        return this;
    }
}
