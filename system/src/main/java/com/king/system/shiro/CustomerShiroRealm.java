package com.king.system.shiro;

import com.king.framework.model.UserInfo;
import com.king.framework.utils.AuthUtils;
import com.king.system.entity.SysResources;
import com.king.system.entity.SysRole;
import com.king.system.entity.SysUser;
import com.king.system.service.ISysResourcesService;
import com.king.system.service.ISysRoleService;
import com.king.system.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @创建人 chq
 * @创建时间 2020/3/29
 * @描述
 */
public class CustomerShiroRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysResourcesService sysResourcesService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录的用户名
        UserInfo userInfo = AuthUtils.getUserInfo();
        String account = userInfo.getUsername();
        //到数据库里查询要授权的内容
        SysUser user = sysUserService.findByUserName(account);
        if(user != null){
            //记录用户的所有角色和权限
            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();//权限信息

            List<SysRole> roles = sysRoleService.getRolesByUserId(user.getId());
            List<SysResources> resources = new ArrayList<>();//所有资源集合
            for(SysRole role : roles){
                //将所有的角色信息添加进来
                simpleAuthorizationInfo.addRole(role.getName());
                resources.addAll(sysResourcesService.getResourceByRoleId(role.getId()));
            }
            Set<String> permissions = getPermissions(resources);
            simpleAuthorizationInfo.addStringPermissions(permissions);
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token获取用户名,从主体传过来的认证信息中获取
        //加这一步的目的是在post请求时会先进入认证然后再到请求。
        if(authenticationToken.getPrincipal()==null){
            return null;
        }
        //获取用户的登录信息，用户名
        String account = authenticationToken.getPrincipal().toString();

        //根据service调用用户名，查找用户的全部信息
        //通过用户名到数据库获取凭证
        SysUser user = sysUserService.findByUserName(account);
        if(user == null){
            //这里返回会报出对应异常
            throw new UnknownAccountException();//没找到帐号
        }else{
            UserInfo userInfo = createUserInfo(user);
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
                    userInfo,
                    user.getPassword(),
                    //ByteSource.Util.bytes(user.getSalt()),//salt=username+salt
                    getName());
            return simpleAuthenticationInfo;
        }
    }

    private UserInfo createUserInfo(SysUser user){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setName(user.getName());
        userInfo.setState(user.getState());
        return userInfo;
    }

    /**
     * 根据菜单功能获取授权信息.
     *
     * @param resources
     * @return
     */
    public static Set<String> getPermissions(List<SysResources> resources) {
        Set<String> permissions = new HashSet<String>();
        String moduleUrl = null;
        for (SysResources resource : resources) {
            moduleUrl = resource.getUrl();
            if (moduleUrl != null) {
                if(moduleUrl.equals("#"))continue;
                if (!permissions.contains(moduleUrl)) {
                    permissions.add(moduleUrl);
                }
            }
        }
        return permissions;
    }

}
