package com.king.em.entity;

import java.io.Serializable;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
public class Experiment implements Serializable {
    private static final long serialVersionUID = -4577205261893406610L;
    private Long id;
    private Integer productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
