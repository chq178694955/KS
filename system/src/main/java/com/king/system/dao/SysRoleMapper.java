package com.king.system.dao;

import com.king.framework.model.Criteria;
import com.king.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int insert(SysRole role);

    List<SysRole> selectAll();

    List<SysRole> getRolesByUserId(Long userId);

    List<SysRole> find(Criteria criteria);

    SysRole selectOne(Long roleId);

    SysRole findByName(String roleName);

    int update(SysRole role);

    int delete(Long roleId);

    int deleteAuthorizationByRoleId(Long roleId);

    int insertAuthorization(@Param("roleId") Long roleId, @Param("resIds") List<Long> resIds);
}