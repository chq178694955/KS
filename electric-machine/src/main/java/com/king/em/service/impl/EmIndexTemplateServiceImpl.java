package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmIndexTemplateDao;
import com.king.em.entity.EmIndexTemplate;
import com.king.em.service.IEmIndexTemplateService;
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
public class EmIndexTemplateServiceImpl implements IEmIndexTemplateService {

    @Autowired
    private EmIndexTemplateDao emIndexTemplateDao;

    @Override
    public EmIndexTemplate findByName(String name) {
        return null;
    }

    @Override
    public boolean add(EmIndexTemplate template) {
        return emIndexTemplateDao.insert(template) > 0 ? true : false;
    }

    @Override
    public boolean update(EmIndexTemplate template) {
        return emIndexTemplateDao.update(template) > 0 ? true : false;
    }

    @Override
    public boolean del(Long id) {
        return emIndexTemplateDao.delete(id) > 0 ? true : false;
    }

    @Override
    public PageInfo<EmIndexTemplate> find(PageInfo<EmIndexTemplate> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmIndexTemplate> list = emIndexTemplateDao.find(criteria);
        PageInfo<EmIndexTemplate> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public EmIndexTemplate findById(Long id) {
        return emIndexTemplateDao.get(id);
    }
}
