package com.king.keke.entity;

import java.io.Serializable;
import java.util.Date;

public class VoteInfo implements Serializable {
    private Long id;

    private String title;

    private Date createTime;

    private String createTimeDesc;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeDesc() {
        return createTimeDesc;
    }

    public void setCreateTimeDesc(String createTimeDesc) {
        this.createTimeDesc = createTimeDesc;
    }
}