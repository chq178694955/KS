package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmAttrType;
import com.king.framework.model.Criteria;

/**
 * @创建人 chq
 * @创建时间 2020/11/20
 * @描述
 */
public interface IEmAttrTypeService {

    EmAttrType findByName(String name);

    PageInfo<EmAttrType> find(PageInfo<EmAttrType> page, Criteria criteria, Boolean isDownload);

    int add(EmAttrType type);

    int update(EmAttrType type);

    int delete(Long id);

}
