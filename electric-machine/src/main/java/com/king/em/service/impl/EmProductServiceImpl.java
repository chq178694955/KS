package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmProductDao;
import com.king.em.entity.EmProduct;
import com.king.em.service.IEmProductService;
import com.king.framework.model.Criteria;
import com.king.framework.utils.AuthUtils;
import com.king.framework.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/22
 * @描述
 */
@Service
public class EmProductServiceImpl implements IEmProductService {

    @Autowired
    private EmProductDao emProductDao;

    @Override
    public boolean add(EmProduct product) {
        return emProductDao.insert(product) > 0 ? true : false;
    }

    @Override
    public boolean delete(Integer id) {
        return emProductDao.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean update(EmProduct product) {
        return emProductDao.update(product) > 0 ? true : false;
    }

    @Override
    public EmProduct findById(Long id) {
        return emProductDao.get("findById",id);
    }

    @Override
    public EmProduct findByName(String name) {
        Long userId = AuthUtils.getUserInfo().getId();
        Criteria criteria = new Criteria();
        criteria.put("userId",userId);
        criteria.put("name",name);
        return emProductDao.get("findByName",criteria);
    }

    @Override
    public PageInfo<EmProduct> find(PageInfo<EmProduct> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmProduct> list = emProductDao.find(criteria);
        for(EmProduct product : list){
            product.setCreateTimeDesc(DateUtils.format(product.getCreateTime(),DateUtils.YMDHMS));
        }
        PageInfo<EmProduct> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public List<EmProduct> findAll(Criteria criteria) {
        return emProductDao.find(criteria);
    }
}
