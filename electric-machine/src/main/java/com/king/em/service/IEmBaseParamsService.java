package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmBaseParams;
import com.king.framework.model.Criteria;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
public interface IEmBaseParamsService {

    PageInfo<EmBaseParams> find(PageInfo<EmBaseParams> page, Criteria criteria, Boolean isDownload);

    int add(EmBaseParams params);

    int update(EmBaseParams params);

    int delete(Integer id);

    EmBaseParams findById(Integer id);

}
