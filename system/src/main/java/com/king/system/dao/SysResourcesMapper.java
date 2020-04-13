package com.king.system.dao;

import com.king.framework.model.Criteria;
import com.king.system.entity.SysResources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysResourcesMapper {
    int insert(SysResources record);

    List<SysResources> selectAll();

    List<SysResources> getResourceByRoleId(Long roleId);

    List<SysResources> findResources(Criteria criteria);
}