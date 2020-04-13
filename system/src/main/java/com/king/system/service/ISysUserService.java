package com.king.system.service;


import com.king.system.entity.SysUser;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/17
 * @描述
 */
public interface ISysUserService {

    List<SysUser> findAll();

    SysUser findByUserName(String username);

    SysUser findByNameAndPass(String username, String password);

}
