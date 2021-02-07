package com.king.keke.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.keke.entity.CarInfo;

import java.util.List;

public interface ICarInfoService {

    List<CarInfo> findAll();

    CarInfo findById(Long id);

    PageInfo<CarInfo> findPage(PageInfo<CarInfo> page, Criteria criteria, boolean isDownload);

    boolean batchAdd(List<CarInfo> cars);

    boolean batchUpdate(List<CarInfo> cars);

    boolean add(CarInfo car);

    boolean update(CarInfo car);

    boolean delete(Long id);

}
