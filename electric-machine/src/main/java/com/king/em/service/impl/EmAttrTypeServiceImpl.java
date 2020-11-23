package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmAttrTypeMapper;
import com.king.em.entity.EmAttrType;
import com.king.em.service.IEmAttrTypeService;
import com.king.framework.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/20
 * @描述
 */
@Service
public class EmAttrTypeServiceImpl implements IEmAttrTypeService {

    @Autowired
    private EmAttrTypeMapper emAttrTypeMapper;

    @Override
    public EmAttrType findByName(String name) {
        return emAttrTypeMapper.findByName(name);
    }

    @Override
    public PageInfo<EmAttrType> find(PageInfo<EmAttrType> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmAttrType> list = emAttrTypeMapper.find(criteria);
        PageInfo<EmAttrType> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public int add(EmAttrType type) {
        return emAttrTypeMapper.insert(type);
    }

    @Override
    public int update(EmAttrType type) {
        return emAttrTypeMapper.update(type);
    }

    @Override
    public int delete(Long id) {
        return emAttrTypeMapper.delete(id);
    }
}
