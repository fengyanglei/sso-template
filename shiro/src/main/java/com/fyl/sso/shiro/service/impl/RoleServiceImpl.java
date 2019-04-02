package com.fyl.sso.shiro.service.impl;

import com.fyl.sso.shiro.dao.RoleMapper;
import com.fyl.sso.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<String> getRoles(String account) {
        return roleMapper.getRoles(account);
    }

}
