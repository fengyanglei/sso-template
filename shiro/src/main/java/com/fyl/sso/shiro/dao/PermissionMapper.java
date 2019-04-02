package com.fyl.sso.shiro.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    /**
     * 获取权限
     * @param account 账号
     * @return
     */
    List<String> getPermissions(@Param("account") String account);
}