package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmIndexDetail;
import com.king.framework.model.Criteria;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
public interface IEmIndexDetailService {

    EmIndexDetail findByName(String name);

    EmIndexDetail findById(Long id);

    boolean add(EmIndexDetail detail);

    boolean update(EmIndexDetail detail);

    boolean delByGroupId(Long groupId);

    boolean del(Long id);

    PageInfo<EmIndexDetail> find(PageInfo<EmIndexDetail> page, Criteria criteria, Boolean isDownload);

    boolean batchAdd(List<EmIndexDetail> details);

    boolean batchUpdate(List<EmIndexDetail> details);

    List<EmIndexDetail> findByGroupId(Long groupId);

}
