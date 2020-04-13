package com.king.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.dao.SysDictMapper;
import com.king.system.entity.SysDict;
import com.king.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
@Service
public class SysDictServiceImpl implements ISysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findAll() {
        return sysDictMapper.selectAll();
    }

    @Override
    public List<SysDict> findTopDicts() {
        return sysDictMapper.selectTopDicts();
    }

    @Override
    public PageInfo<SysDict> find(PageInfo<SysDict> page,Criteria criteria,Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<SysDict> dicts = sysDictMapper.select(criteria);
        PageInfo<SysDict> pageInfo = new PageInfo<>(dicts);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public boolean isExistsDict(Long dictSn) {
        SysDict dict = sysDictMapper.selectOne(dictSn);
        return dict != null ? true : false;
    }

    @Override
    public int addDict(SysDict dict) {
        return sysDictMapper.insert(dict);
    }

    @Override
    public int updateDict(SysDict dict) {
        return sysDictMapper.update(dict);
    }

    @Override
    public int deleteDict(Long dictSn) {
        return sysDictMapper.delete(dictSn);
    }
}
