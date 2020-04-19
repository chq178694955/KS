package com.king.system.service.impl;

import com.king.framework.model.Criteria;
import com.king.system.dao.SysResourcesMapper;
import com.king.system.entity.SysResources;
import com.king.system.service.ISysResourcesService;
import com.king.system.vo.SysResourcesVO;
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

    @Override
    public SysResourcesVO findById(Long resId) {
        return sysResourcesMapper.selectOne(resId);
    }

    @Override
    public int addResource(SysResources res) {
        return sysResourcesMapper.insert(res);
    }

    @Override
    public int updateResource(SysResources res) {
        return sysResourcesMapper.update(res);
    }

    @Override
    public int delResource(Long resId) {
        return sysResourcesMapper.delete(resId);
    }

    @Override
    public SysResources findByName(String name) {
        return sysResourcesMapper.selectByName(name);
    }
}
