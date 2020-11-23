package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmIndexCategoryDao;
import com.king.em.entity.EmIndexCategory;
import com.king.em.service.IEmIndexCategoryService;
import com.king.framework.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/23
 * @描述
 */
@Service
public class EmIndexCategoryServiceImpl implements IEmIndexCategoryService {

    @Autowired
    private EmIndexCategoryDao emIndexCategoryDao;

    @Override
    public EmIndexCategory findByName(String name) {
        return null;
    }

    @Override
    public boolean add(EmIndexCategory category) {
        return emIndexCategoryDao.insert(category) > 0 ? true : false;
    }

    @Override
    public boolean update(EmIndexCategory category) {
        return emIndexCategoryDao.update(category) > 0 ? true : false;
    }

    @Override
    public boolean del(Integer id) {
        return emIndexCategoryDao.delete(id) > 0 ? true : false;
    }

    @Override
    public PageInfo<EmIndexCategory> find(PageInfo<EmIndexCategory> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmIndexCategory> list = emIndexCategoryDao.find(criteria);
        PageInfo<EmIndexCategory> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public EmIndexCategory findById(Integer id) {
        return emIndexCategoryDao.get(id);
    }

    @Override
    public List<EmIndexCategory> findAll() {
        return emIndexCategoryDao.findAll();
    }
}
