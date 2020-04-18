package com.king.system.service;


import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.entity.SysUser;
import com.king.system.vo.SysUserVO;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/17
 * @描述
 */
public interface ISysUserService {

    SysUser findByUserName(String username);

    PageInfo<SysUserVO> find(PageInfo<SysUser> page, Criteria criteria, Boolean isDownload);

    int addUser(SysUser user);

    int updateUser(SysUserVO user);

    int delUser(Long userId);

}
