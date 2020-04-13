package com.king.system.dao;

import com.king.system.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int insert(SysRole record);

    List<SysRole> selectAll();

    List<SysRole> getRolesByUserId(Long userId);
}