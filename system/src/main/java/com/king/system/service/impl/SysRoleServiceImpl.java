package com.king.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.dao.SysRoleMapper;
import com.king.system.entity.SysRole;
import com.king.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int addRole(SysRole role) {
        return sysRoleMapper.insert(role);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.selectAll();
    }

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        return sysRoleMapper.getRolesByUserId(userId);
    }

    @Override
    public PageInfo<SysRole> find(PageInfo<SysRole> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<SysRole> roles = sysRoleMapper.find(criteria);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roles);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }
}
