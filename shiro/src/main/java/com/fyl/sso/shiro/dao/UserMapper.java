package com.fyl.sso.shiro.dao;

import com.fyl.sso.shiro.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 根据账号获取用户
     *
     * @param account 账号
     * @return
     */
    User getUserByAccount(@Param("account") String account);
}