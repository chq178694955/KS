package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmDataConstantloadDao;
import com.king.em.dao.EmDataSpeedDao;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.EmDataConstantload;
import com.king.em.entity.EmDataSpeed;
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
@Service("emDataConstantloadService")
public class EmDataConstantloadServiceImpl implements IExperimentDataMgrService<Experiment> {

    @Autowired
    private EmDataConstantloadDao emDataConstantloadDao;

    @Override
    public ExperimentType getExperimentType() {
        return ExperimentType.CONSTANTLOAD;
    }

    @Override
    public boolean batchInsert(List<Experiment> list) {
        List<EmDataConstantload> list2 = new ArrayList<>();
        for(Experiment entity : list){
            EmDataConstantload load = (EmDataConstantload)entity;
            list2.add(load);
        }
        return emDataConstantloadDao.batchInsert("insert",list2) > 0 ? true : false;
    }

    @Override
    public boolean delete(List<Long> ids) {
        return emDataConstantloadDao.batchDelete(ids) > 0 ? true : false;
    }

    @Override
    public boolean deleteByProductId(Integer productId) {
        return emDataConstantloadDao.delete("deleteByProductId",productId) > 0 ? true : false;
    }

    @Override
    public List<Experiment> findAll() {
        return emDataConstantloadDao.findAll();
    }

    @Override
    public PageInfo<Experiment> find(PageInfo<Experiment> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<Experiment> list = emDataConstantloadDao.find(criteria);
        PageInfo<Experiment> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }
}
