package com.king.framework.ds;

/**
 * 数据源类型
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
public enum DataSourceType {

    MASTER(0,"MASTER"),SLAVE(1,"SLAVE"),SHARE(2,"SHARE");

    private int index;

    private String name;

    public int getIndex(){
        return this.index;
    }

    public String getName(){
        return this.name;
    }

    DataSourceType(int index,String name){
        this.index = index;
        this.name = name;
    }

    public static DataSourceType getType(String name){
        for(DataSourceType type : DataSourceType.values()){
            if(type.getName().equals(name))return type;
        }
        return null;
    }

}
