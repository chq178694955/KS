package com.king.keke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.keke.dao.CarInfoDao;
import com.king.keke.entity.CarInfo;
import com.king.keke.service.ICarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carInfoService")
public class CarInfoServiceImpl implements ICarInfoService {

    @Autowired
    private CarInfoDao carInfoDao;

    @Override
    public List<CarInfo> findAll() {
        return carInfoDao.findAll();
    }

    @Override
    public CarInfo findById(Long id) {
        return carInfoDao.get(id);
    }

    @Override
    public PageInfo<CarInfo> findPage(PageInfo<CarInfo> page, Criteria criteria, boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<CarInfo> cars = carInfoDao.find(criteria);
        PageInfo<CarInfo> result = new PageInfo<>(cars);
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }

    @Override
    public boolean batchAdd(List<CarInfo> cars) {
        return carInfoDao.batchInsert(cars) > 0 ? true : false;
    }

    @Override
    public boolean batchUpdate(List<CarInfo> cars) {
        return carInfoDao.batchUpdate(cars) > 0 ? true : false;
    }

    @Override
    public boolean add(CarInfo car) {
        return carInfoDao.insert(car) > 0 ? true : false;
    }

    @Override
    public boolean update(CarInfo car) {
        return carInfoDao.update(car) > 0 ? true : false;
    }

    @Override
    public boolean delete(Long id) {
        return carInfoDao.delete(id) > 0 ? true : false;
    }
}
