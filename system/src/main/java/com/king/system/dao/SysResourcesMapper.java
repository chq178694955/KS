package com.king.system.dao;

import com.king.framework.model.Criteria;
import com.king.system.entity.SysResources;
import com.king.system.vo.SysResourcesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysResourcesMapper {

    int insert(SysResources record);

    int update(SysResources record);

    int delete(Long resId);

    List<SysResources> selectAll();

    List<SysResources> getResourceByRoleId(Long roleId);

    List<SysResources> findResources(Criteria criteria);

    SysResourcesVO selectOne(Long resId);

    SysResources selectByName(String name);
}