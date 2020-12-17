package com.king.keke.entity;

import java.io.Serializable;
import java.util.List;

public class VoteOption implements Serializable {
    private Long id;

    private String name;

    private Short type;

    private Long voteId;

    private List<VoteOptionItem> items;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public List<VoteOptionItem> getItems() {
        return items;
    }

    public void setItems(List<VoteOptionItem> items) {
        this.items = items;
    }
}