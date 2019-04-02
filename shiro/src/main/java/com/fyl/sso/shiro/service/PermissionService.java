package com.fyl.sso.shiro.service;

import java.util.List;

/**
 * 权限
 */
public interface PermissionService {

    /**
     * 获取权限
     *
     * @param account 账号
     * @return
     */
    List<String> getPermissions(String account);
}
