package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmIndexCategory;
import com.king.framework.model.Criteria;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
public interface IEmIndexCategoryService {

    EmIndexCategory findByName(String name);

    EmIndexCategory findById(Integer id);

    boolean add(EmIndexCategory category);

    boolean update(EmIndexCategory category);

    boolean del(Integer id);

    PageInfo<EmIndexCategory> find(PageInfo<EmIndexCategory> page, Criteria criteria, Boolean isDownload);

    List<EmIndexCategory> findAll();

}
