package com.king.em.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.em.dao.EmBaseParamsMapper;
import com.king.em.entity.EmBaseParams;
import com.king.em.service.IEmBaseParamsService;
import com.king.framework.model.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
@Service
public class EmBaseParamsServiceImpl implements IEmBaseParamsService {

    @Autowired
    private EmBaseParamsMapper emBaseParamsMapper;

    @Override
    public PageInfo<EmBaseParams> find(PageInfo<EmBaseParams> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<EmBaseParams> list = emBaseParamsMapper.find(criteria);
        PageInfo<EmBaseParams> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public int add(EmBaseParams params) {
        return emBaseParamsMapper.insert(params);
    }

    @Override
    public int update(EmBaseParams params) {
        return emBaseParamsMapper.update(params);
    }

    @Override
    public int delete(Integer id) {
        return emBaseParamsMapper.delete(id);
    }

    @Override
    public EmBaseParams findById(Integer id) {
        return emBaseParamsMapper.selectOne(id);
    }

    @Override
    public EmBaseParams findDefault() {
        return emBaseParamsMapper.selectDefault();
    }
}
