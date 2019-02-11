package com.family.controller;

import com.family.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping(value="toLogin")
    public String toLogin(){
        return "/base/login";
    }

    @GetMapping(value="loginSuccess")
    public String userList(){
        return "/user/list";
    }

    @GetMapping(value="toRegister")
    public String toRegister(){
        return "/base/register";
    }

}
