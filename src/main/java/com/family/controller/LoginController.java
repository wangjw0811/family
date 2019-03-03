package com.family.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {
    //首页
    @GetMapping(value="/")
    public String toIndex(){
        return "base/index";
    }

    //登录界面
    @GetMapping(value="toLogin")
    public String toLogin(){
        return "base/login";
    }

    //登录成功界面
    @GetMapping(value="loginSuccess")
    public String userList(){
        return "base/success";
    }

    //用户登出
    @GetMapping(value = "logOut")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);//防止创建Session
        if(session != null){
            session.removeAttribute("user");
        }
        return "redirect:/";
    }

    //注册界面
    @GetMapping(value="toRegister")
    public String toRegister(){
        return "base/register";
    }

    //用户列表界面
    @GetMapping(value = "toUsers")
    public String toUsers(){
        return "user/users";
    }

    //姓氏列表界面
    @GetMapping(value = "toHalls")
    public String toHalls(){
        return "hall/halls";
    }

    //姓氏信息详情
    @GetMapping(value = "toHallDetail")
    public String hallDetail(){
        return "hall/hallDetail";
    }

    //族员列表界面
    @GetMapping(value = "toPeoples")
    public String toPeoples(){
        return "people/peoples";
    }

    //新增族员信息
    @GetMapping(value = "toAddPeople")
    public String addPeoples(){
        return "people/addPeople";
    }

    //族员信息详情
    @GetMapping(value = "toPeopleDetail")
    public String peopleDetail(){
        return "people/peopleDetail";
    }

}
