package com.fyl.sso.shiro.service;

import com.fyl.sso.shiro.model.User;

/**
 * 用户
 */
public interface UserService {

    /**
     * 根据账号获取用户信息
     *
     * @param account 账号
     * @return
     */
    User getUserByAccount(String account);

}
