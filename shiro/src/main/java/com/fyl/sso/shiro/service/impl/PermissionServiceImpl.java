package com.fyl.sso.shiro.service.impl;

import com.fyl.sso.shiro.service.PermissionService;
import com.fyl.sso.shiro.dao.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<String> getPermissions(String account) {
        return permissionMapper.getPermissions(account);
    }
}
