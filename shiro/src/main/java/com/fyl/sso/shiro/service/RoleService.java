package com.fyl.sso.shiro.service;

import java.util.List;

/**
 * 角色
 */
public interface RoleService {

    /**
     * 获取角色
     *
     * @param account 账号
     * @return
     */
    List<String> getRoles(String account);
}
