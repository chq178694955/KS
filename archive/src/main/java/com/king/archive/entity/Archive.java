package com.king.archive.entity;

import java.io.Serializable;

public abstract class Archive implements Serializable {

    private static final long serialVersionUID = 9130159484038651473L;

    private Long id;
    
    private String name;

    
    public Long getId() {
    
        return id;
    }

    
    public void setId(Long id) {
    
        this.id = id;
    }

    public String getName() {
    
        return name;
    }
    
    public void setName(String name) {
    
        this.name = name;
    }

    
}
