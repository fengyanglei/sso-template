package com.fyl.sso.shiro.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    /**
     * 获取角色
     * @param account 账号
     * @return
     */
    List<String> getRoles(@Param("account") String account);
}