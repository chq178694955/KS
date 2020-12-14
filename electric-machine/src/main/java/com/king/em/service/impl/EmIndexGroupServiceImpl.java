package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmIndexGroupDao;
import com.king.em.entity.EmIndexGroup;
import com.king.em.service.IEmIndexGroupService;
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
public class EmIndexGroupServiceImpl implements IEmIndexGroupService {

    @Autowired
    private EmIndexGroupDao EmIndexGroupDao;

    @Override
    public List<EmIndexGroup> findAll() {
        return EmIndexGroupDao.findAll();
    }

    @Override
    public EmIndexGroup findByName(Criteria criteria) {
        return EmIndexGroupDao.get("findByName",criteria);
    }

    @Override
    public boolean add(EmIndexGroup group) {
        return EmIndexGroupDao.insert(group) > 0 ? true : false;
    }

    @Override
    public boolean update(EmIndexGroup group) {
        return EmIndexGroupDao.update(group) > 0 ? true : false;
    }

    @Override
    public boolean del(Long id) {
        return EmIndexGroupDao.delete(id) > 0 ? true : false;
    }

    @Override
    public PageInfo<EmIndexGroup> find(PageInfo<EmIndexGroup> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmIndexGroup> list = EmIndexGroupDao.find(criteria);
        PageInfo<EmIndexGroup> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public EmIndexGroup findById(Long id) {
        return EmIndexGroupDao.get(id);
    }
}
