package com.king.framework.echarts.component;

/**
 * @创建人 chq
 * @创建时间 2020/5/1
 * @描述
 */
public class Title extends Component{

    private String text;

    private String subtext;

    private String x;

    private String y;

    public Title(String text){
        this.text = text;
    }

    public Title(String text,String subtext){
        this.text = text;
        this.subtext = subtext;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getX() {
        return x;
    }

    public Title setX(String x) {
        this.x = x;
        return this;
    }

    public String getY() {
        return y;
    }

    public Title setY(String y) {
        this.y = y;
        return this;
    }

}
