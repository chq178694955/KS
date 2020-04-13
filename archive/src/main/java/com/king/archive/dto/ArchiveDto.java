package com.king.archive.dto;

import java.io.Serializable;

/**
 * 档案对象基类
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
public class ArchiveDto implements Serializable,Cloneable {

    private static final long serialVersionUID = 1050521322992293140L;

    // 档案类型
    private ArchiveType archiveType;

    // 处理类型
    private HandlerType handlerType;

    // 档案唯一标识
    private long id;

    // 档案名称
    private String name;

    public ArchiveDto(ArchiveType type) {

        this.archiveType = type;
    }

    public HandlerType getHandlerType() {

        return handlerType;
    }

    public void setHandlerType(HandlerType handlerType) {

        this.handlerType = handlerType;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public ArchiveType getArchiveType() {

        return archiveType;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setArchiveType(ArchiveType archiveType) {

        this.archiveType = archiveType;
    }



}
