package com.king.system.service.impl;

import com.king.framework.model.Criteria;
import com.king.system.dao.SysResourcesMapper;
import com.king.system.entity.SysResources;
import com.king.system.service.ISysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
@Service
public class SysResourcesServiceImpl implements ISysResourcesService {

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public List<SysResources> getResourceByRoleId(Long roleId) {
        return sysResourcesMapper.getResourceByRoleId(roleId);
    }

    @Override
    public List<SysResources> findResources(Criteria criteria) {
        return sysResourcesMapper.findResources(criteria);
    }

    @Override
    public List<SysResources> findAll() {
        return sysResourcesMapper.selectAll();
    }
}
