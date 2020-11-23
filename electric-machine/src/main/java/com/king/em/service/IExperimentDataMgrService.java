package com.king.em.service;

import com.github.pagehelper.PageInfo;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.Experiment;
import com.king.framework.model.Criteria;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
public interface IExperimentDataMgrService<T extends Experiment> {

    ExperimentType getExperimentType();

    boolean batchInsert(List<T> list);

    boolean deleteByProductId(Integer productId);

    boolean delete(List<Long> ids);

    List<T> findAll();

    PageInfo<T> find(PageInfo<T> page,Criteria criteria,Boolean isDownload);

}
