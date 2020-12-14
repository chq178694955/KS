package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmIndexGroup;
import com.king.framework.model.Criteria;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
public interface IEmIndexGroupService {

    List<EmIndexGroup> findAll();

    EmIndexGroup findByName(Criteria criteria);

    EmIndexGroup findById(Long id);

    boolean add(EmIndexGroup group);

    boolean update(EmIndexGroup group);

    boolean del(Long id);

    PageInfo<EmIndexGroup> find(PageInfo<EmIndexGroup> page, Criteria criteria, Boolean isDownload);

}
