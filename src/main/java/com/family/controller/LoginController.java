package com.family.controller;

import com.family.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping(value="toLogin")
    @ApiOperation(value = "登录界面")
    public String toLogin(){
        return "base/login";
    }
    @ApiOperation(value = "登录成功界面")
    @GetMapping(value="loginSuccess")
    public String userList(){
        return "base/success";
    }

    @ApiOperation(value = "注册界面")
    @GetMapping(value="toRegister")
    public String toRegister(){
        return "base/register";
    }

    @ApiOperation(value = "用户列表界面")
    @GetMapping(value = "toUsers")
    public String toUsers(){
        return "user/users";
    }

    @ApiOperation(value = "姓氏列表界面")
    @GetMapping(value = "toHalls")
    public String toHalls(){
        return "hall/halls";
    }

    @ApiOperation(value = "族员列表界面")
    @GetMapping(value = "toPeoples")
    public String toPeoples(){
        return "people/peoples";
    }

    @ApiOperation(value = "新增族员信息")
    @GetMapping(value = "toAddPeople")
    public String addPeoples(){
        return "people/addPeople";
    }

    @ApiOperation(value = "族员信息详情")
    @GetMapping(value = "toPeopleDetail")
    public String peopleDetail(){
        return "people/peopleDetail";
    }

}
