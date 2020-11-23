package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.entity.EmIndexDetail;
import com.king.em.service.IEmIndexDetailService;
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
public class EmIndexDetailServiceImpl implements IEmIndexDetailService {

    @Autowired
    private com.king.em.dao.EmIndexDetailDao EmIndexDetailDao;

    @Override
    public EmIndexDetail findByName(String name) {
        return null;
    }

    @Override
    public boolean add(EmIndexDetail detail) {
        return EmIndexDetailDao.insert(detail) > 0 ? true : false;
    }

    @Override
    public boolean update(EmIndexDetail detail) {
        return EmIndexDetailDao.update(detail) > 0 ? true : false;
    }

    @Override
    public boolean del(Long id) {
        return EmIndexDetailDao.delete(id) > 0 ? true : false;
    }

    @Override
    public PageInfo<EmIndexDetail> find(PageInfo<EmIndexDetail> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmIndexDetail> list = EmIndexDetailDao.find(criteria);
        PageInfo<EmIndexDetail> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public EmIndexDetail findById(Long id) {
        return EmIndexDetailDao.get(id);
    }
}
