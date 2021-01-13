package com.king.keke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.keke.dao.ResidentInfoDao;
import com.king.keke.entity.ResidentInfo;
import com.king.keke.service.IResidentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("residentInfoService")
public class ResidentInfoServiceImpl implements IResidentInfoService {

    @Autowired
    private ResidentInfoDao residentInfoDao;

    @Override
    public PageInfo<ResidentInfo> findPage(PageInfo<ResidentInfo> page, Criteria criteria, boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<ResidentInfo> infos = residentInfoDao.find(criteria);
        PageInfo<ResidentInfo> result = new PageInfo<>(infos);
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public List<ResidentInfo> findAll() {
        return residentInfoDao.findAll();
    }

    @Override
    public ResidentInfo findByBuildAndHouse(ResidentInfo info) {
        return residentInfoDao.get("findByBuildAndHouse",info);
    }

    @Override
    public ResidentInfo findById(Long id) {
        return residentInfoDao.get("findById",id);
    }

    @Override
    public boolean batchAdd(List<ResidentInfo> infos) {
        return residentInfoDao.batchInsert(infos) > 0 ? true : false;
    }

    @Override
    public boolean batchModify(List<ResidentInfo> infos) {
        return residentInfoDao.batchUpdate(infos) > 0 ? true : false;
    }

    @Override
    public boolean batchDelete(List<Long> ids) {
        return residentInfoDao.batchDelete(ids) > 0 ? true : false;
    }

    @Override
    public boolean add(ResidentInfo info) {
        return residentInfoDao.insert(info) > 0 ? true : false;
    }

    @Override
    public boolean modify(ResidentInfo info) {
        return residentInfoDao.update(info) > 0 ? true : false;
    }

    @Override
    public boolean del(Long id) {
        return residentInfoDao.delete(id) > 0 ? true :false;
    }

    @Override
    public List<ResidentInfo> findBuildings() {
        return residentInfoDao.find("findBuildings",null);
    }
}
