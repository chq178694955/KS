package com.king.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.dao.SysUserDao;
import com.king.system.entity.SysUser;
import com.king.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/17
 * @描述
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> findAll() {
        //分页设置放在查询之前
        PageHelper.startPage(1, 1);
        List<SysUser> list = sysUserDao.findAll();//sysUserMapper.findAll();
//        Long totalCount = sysUserMapper.findCount(params);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        return list;
    }

    @Override
    public SysUser findByUserName(String username) {
        Criteria criteria = new Criteria();
        criteria.put("username",username);
        return sysUserDao.get("findByUserName",criteria);
    }

    @Override
    public SysUser findByNameAndPass(String username, String password) {
        Criteria criteria = new Criteria();
        criteria.put("username",username);
        criteria.put("password",password);
        return sysUserDao.get("findByNameAndPass",criteria);
    }
}
