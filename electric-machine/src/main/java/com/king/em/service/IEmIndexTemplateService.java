package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmIndexTemplate;
import com.king.framework.model.Criteria;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
public interface IEmIndexTemplateService {

    EmIndexTemplate findByName(String name);

    EmIndexTemplate findById(Long id);

    boolean add(EmIndexTemplate template);

    boolean update(EmIndexTemplate template);

    boolean del(Long id);

    PageInfo<EmIndexTemplate> find(PageInfo<EmIndexTemplate> page, Criteria criteria, Boolean isDownload);

}
