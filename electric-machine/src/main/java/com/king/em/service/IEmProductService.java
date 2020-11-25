package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmProduct;
import com.king.framework.model.Criteria;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/22
 * @描述
 */
public interface IEmProductService {

    boolean add(EmProduct product);

    boolean delete(Long id);

    boolean update(EmProduct product);

    EmProduct findById(Long name);

    EmProduct findByName(String name);

    PageInfo<EmProduct> find(PageInfo<EmProduct> page, Criteria criteria, Boolean isDownload);

    List<EmProduct> findAll(Criteria criteria);

}
