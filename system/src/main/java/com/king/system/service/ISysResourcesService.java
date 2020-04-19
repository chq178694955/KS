package com.king.system.service;

import com.king.framework.model.Criteria;
import com.king.system.entity.SysResources;
import com.king.system.vo.SysResourcesVO;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
public interface ISysResourcesService {

    List<SysResources> getResourceByRoleId(Long roleId);

    List<SysResources> findResources(Criteria criteria);

    List<SysResources> findAll();

    SysResourcesVO findById(Long resId);

    int addResource(SysResources res);

    int updateResource(SysResources res);

    int delResource(Long resId);

    SysResources findByName(String name);

}
