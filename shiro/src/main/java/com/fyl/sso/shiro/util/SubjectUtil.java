package com.fyl.sso.shiro.util;

import com.fyl.sso.shiro.model.User;
import org.apache.shiro.SecurityUtils;

/**
 * shiro 获取登录用户信息工具
 */
public class SubjectUtil {
    /**
     * 用户session key
     */
    public static final String USER = "user";

    /**
     * 登录用户信息
     *
     * @return
     */
    public static User getUser() {
        return (User) SecurityUtils.getSubject().getSession().getAttribute(USER);
    }

    /**
     * 登录账号
     *
     * @return
     */
    public static String getAccount() {
        return (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
    }

}
