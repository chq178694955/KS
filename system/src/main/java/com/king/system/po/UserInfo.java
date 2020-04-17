package com.king.system.po;

import com.king.system.entity.SysUser;

import java.io.Serializable;

/**
 * 登录后存储的用户信息对象
 * @创建人 chq
 * @创建时间 2020/4/17
 * @描述
 */
public class UserInfo implements Serializable {

    public UserInfo(){

    }

    private Long id;

    private String username;

    private String name;

    private String idCardNum;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
