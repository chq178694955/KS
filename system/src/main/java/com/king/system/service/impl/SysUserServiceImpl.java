package com.king.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.framework.utils.MD5Util;
import com.king.system.conts.UserStateEnum;
import com.king.system.dao.SysUserDao;
import com.king.system.entity.SysUser;
import com.king.system.service.ISysUserService;
import com.king.system.vo.SysUserVO;
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
    public SysUser findByUserName(String username) {
        Criteria criteria = new Criteria();
        criteria.put("username",username);
        return sysUserDao.get("findByUserName",criteria);
    }

    @Override
    public PageInfo<SysUserVO> find(PageInfo<SysUser> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<SysUserVO> users = sysUserDao.find(criteria);
        if(null != users && users.size() > 0){
            for(SysUserVO vo : users){
                vo.setStateDesc(UserStateEnum.fromValue(vo.getState()));
            }
        }
        PageInfo<SysUserVO> pageInfo = new PageInfo<>(users);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public int addUser(SysUser user) {
        user.setPassword(MD5Util.encode(user.getPassword()));
        return sysUserDao.insert("insert", user);
    }

    @Override
    public int updateUser(SysUserVO user) {
        SysUser u = new SysUser();
        u.setId(user.getId());
        u.setName(user.getName());
        u.setIdCardNum(user.getIdCardNum());
        if(user.getRoleId() > 0){
            //修改用户角色关系
            sysUserDao.delete("delUserRole",user.getId());
            Criteria criteria = new Criteria();
            criteria.put("userId",user.getId());
            criteria.put("roleId",user.getRoleId());
            sysUserDao.insert("addUserRole",user);
        }
        return sysUserDao.update("update", u);
    }

    @Override
    public int delUser(Long userId) {
        return sysUserDao.delete("delete", userId);
    }
}
