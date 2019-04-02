package com.fyl.sso.shiro.realm;

import com.fyl.sso.shiro.model.User;
import com.fyl.sso.shiro.service.PermissionService;
import com.fyl.sso.shiro.service.RoleService;
import com.fyl.sso.shiro.service.UserService;
import com.fyl.sso.shiro.util.SubjectUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 用户授权信息域
 */
public class UserRealm extends CasRealm {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    /**
     * 设置角色和权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String account = (String) principals.getPrimaryPrincipal();
        authorizationInfo.addStringPermissions(permissionService.getPermissions(account));
        authorizationInfo.addRoles(roleService.getRoles(account));
        return authorizationInfo;
    }


    /**
     * 1、CAS认证 ,验证用户身份
     * 2、将用户基本信息设置到会话中
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
        String account = (String) authc.getPrincipals().getPrimaryPrincipal();
        User user = userService.getUserByAccount(account);
        user.setPassword(null);
        SecurityUtils.getSubject().getSession().setAttribute(SubjectUtil.USER, user);
        return authc;
    }


}
