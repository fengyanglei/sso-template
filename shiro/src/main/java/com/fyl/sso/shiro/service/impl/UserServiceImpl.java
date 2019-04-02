package com.fyl.sso.shiro.service.impl;

import com.fyl.sso.shiro.dao.UserMapper;
import com.fyl.sso.shiro.model.User;
import com.fyl.sso.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

}
