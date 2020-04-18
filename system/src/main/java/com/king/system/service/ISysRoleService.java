package com.king.system.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.entity.SysRole;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
public interface ISysRoleService {

    int addRole(SysRole role);

    List<SysRole> findAll();

    List<SysRole> getRolesByUserId(Long userId);

    PageInfo<SysRole> find(PageInfo<SysRole> page, Criteria criteria, Boolean isDownload);

}
