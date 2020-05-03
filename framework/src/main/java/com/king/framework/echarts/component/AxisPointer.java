package com.king.framework.echarts.component;

/**
 * @创建人 chq
 * @创建时间 2020/5/2
 * @描述
 */
public abstract class AxisPointer {

    private String type;

    public abstract void buildStyle();

    public enum AxisPointerType{
        LINE("line"),CROSS("cross"),SHADOW("shadow"),NONE("none");
        private String val;
        AxisPointerType(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
