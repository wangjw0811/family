package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.family.entity.User;
import com.family.enums.ResultCode;
import com.family.service.impl.UserServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-01-07
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(description = "用户管理")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping(value="user")
    @ApiOperation(value = "查询")
    @ResponseBody
    public ResponseResult list(User user){
        IPage<User> page = new Page<>(1,10);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        IPage<Map<String, Object>> mapIPage = userService.pageMaps(page, wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), mapIPage);
    }

    @GetMapping(value="checkUser")
    @ApiOperation(value = "查询")
    @ResponseBody
    public ResponseResult checkUser(User user, String account, HttpServletRequest request, HttpServletResponse response){
        Object session = request.getSession().getAttribute("user");
        if(session != null){//若session 不为空，直接跳转登录成功
            log.info(session.toString());
            return new ResponseResult(ResultCode.SUCCESS.getIndex(),ResultCode.SUCCESS.getMessage(),user);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String password = user.getPassword();
        user.setEmail(account);//先通过邮箱匹配
        user.setPassword(Common.md5(password));
        wrapper.setEntity(user);
        User user_email = userService.getOne(wrapper);
        if(user_email == null){//邮箱匹配失败再通过身份证号匹配
            user.setEmail(null);
            user.setIdNumber(Common.md5(account));
            wrapper.setEntity(user);
            user = userService.getOne(wrapper);
        }
        if(user != null){
            request.getSession().setAttribute("user",user);//登录成功，将用户数据放入到Session中
            return new ResponseResult(ResultCode.SUCCESS.getIndex(),ResultCode.SUCCESS.getMessage(),user);
        }else{
            return new ResponseResult(ResultCode.FAILURE.getIndex(),"账号或密码错误",user);
        }
    }

    @PutMapping(value = "user")
    @ResponseBody
    public ResponseResult register(User user){
        user.setId(Common.getId());
        boolean flag;
        try {
            flag = userService.save(user);
        }catch (Exception e){
            log.error(e.toString());
            return new ResponseResult(500,"系统错误，请联系管理员",user);
        }
        if(flag){
            return new ResponseResult(200,"注册成功",user);
        }else{
            return new ResponseResult(-1,"注册失败",user);
        }
    }

    @PostMapping(value="user")
    @ApiOperation(value = "修改")
    @ResponseBody
    public boolean update(User user){
        return userService.updateById(user);
    }

    @DeleteMapping(value="user")
    @ApiOperation(value="删除",notes = "根据id删除")
    @ResponseBody
    public boolean delete(Integer id){
       return userService.removeById(id);
    }

}
