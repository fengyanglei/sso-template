package com.fyl.sso.client.controller;

import com.fyl.sso.shiro.util.SubjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping
    public String home() {
        return "index";
    }

    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("name", SubjectUtil.getUser().getAccount());
        return "index";
    }


    @ResponseBody
    @GetMapping("hello")
    public String hello() {
        return "hello world!";
    }

    @ResponseBody
    @GetMapping("user")
    public String user() {
        return SubjectUtil.getUser().getAccount();
    }

}
