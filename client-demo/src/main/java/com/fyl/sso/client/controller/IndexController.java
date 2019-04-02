package com.fyl.sso.client.controller;

import com.fyl.sso.shiro.model.User;
import com.fyl.sso.shiro.util.SubjectUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IndexController {

    @RequestMapping
    public String home() {
        return "hello world";
    }

    @RequestMapping("index")
    public String index() {
        return "index hello world";
    }

    @RequestMapping("login")
    public String login() {
        return "login hello world";
    }

    @RequestMapping("logout")
    public String logout() {
        return "logout hello world";
    }

    @RequestMapping("user")
    public String user() {
        User user = SubjectUtil.getUser();
        return user.getAccount();
    }

}
