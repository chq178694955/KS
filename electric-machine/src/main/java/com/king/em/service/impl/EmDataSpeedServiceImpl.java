package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmDataEmptyloadDao;
import com.king.em.dao.EmDataSpeedDao;
import com.king.em.dto.ExperimentType;
import com.king.em.entity.EmDataEmptyload;
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
@Service("emDataSpeedService")
public class EmDataSpeedServiceImpl implements IExperimentDataMgrService<Experiment> {

    @Autowired
    private EmDataSpeedDao emDataSpeedDao;

    @Override
    public ExperimentType getExperimentType() {
        return ExperimentType.SPEED;
    }

    @Override
    public boolean batchInsert(List<Experiment> list) {
        List<EmDataSpeed> list2 = new ArrayList<>();
        for(Experiment entity : list){
            EmDataSpeed speed = (EmDataSpeed)entity;
            list2.add(speed);
        }
        return emDataSpeedDao.batchInsert("insert",list2) > 0 ? true : false;
    }

    @Override
    public boolean delete(List<Long> ids) {
        return emDataSpeedDao.batchDelete(ids) > 0 ? true : false;
    }

    @Override
    public boolean deleteByProductId(Integer productId) {
        return emDataSpeedDao.delete("deleteByProductId",productId) > 0 ? true : false;
    }

    @Override
    public List<Experiment> findAll() {
        return emDataSpeedDao.findAll();
    }

    @Override
    public PageInfo<Experiment> find(PageInfo<Experiment> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<Experiment> list = emDataSpeedDao.find(criteria);
        PageInfo<Experiment> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }
}
