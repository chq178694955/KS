package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmDataSinDao;
import com.king.em.dao.EmDataStepDao;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.EmDataSin;
import com.king.em.entity.EmDataStep;
import com.king.em.entity.Experiment;
import com.king.em.service.IExperimentDataMgrService;
import com.king.framework.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
@Service("emDataSinService")
public class EmDataSinServiceImpl implements IExperimentDataMgrService<Experiment> {

    @Autowired
    private EmDataSinDao emDataSinDao;

    @Override
    public ExperimentType getExperimentType() {
        return ExperimentType.SIN;
    }

    @Override
    public boolean batchInsert(List<Experiment> list) {
        List<EmDataSin> list2 = new ArrayList<>();
        for(Experiment entity : list){
            EmDataSin sin = (EmDataSin)entity;
            list2.add(sin);
        }
        return emDataSinDao.batchInsert("insert",list2) > 0 ? true : false;
    }

    @Override
    public boolean delete(List<Long> ids) {
        return emDataSinDao.batchDelete(ids) > 0 ? true : false;
    }

    @Override
    public boolean deleteByProductId(Integer productId) {
        return emDataSinDao.delete("deleteByProductId",productId) > 0 ? true : false;
    }

    @Override
    public List<Experiment> findAll() {
        return emDataSinDao.findAll();
    }

    @Override
    public PageInfo<Experiment> find(PageInfo<Experiment> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<Experiment> list = emDataSinDao.find(criteria);
        PageInfo<Experiment> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }
}
