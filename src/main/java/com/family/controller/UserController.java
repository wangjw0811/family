package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.family.entity.User;
import com.family.service.impl.UserServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import freemarker.template.utility.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public IPage<User> list(User user){
        IPage<User> page = new Page<>(1,10);
        QueryWrapper<User> wrapper = new QueryWrapper<User>().like("name",user.getName());
        return userService.page(page);
    }

    @GetMapping(value="checkUser")
    @ApiOperation(value = "查询")
    @ResponseBody
    public ResponseResult checkUser(User user,String email){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringHelper.isNullOrEmptyString(email)){
            user.setEmail(email);
        }
        wrapper.setEntity(user);
        String password = user.getPassword();
        user = userService.getOne(wrapper);
        if(password == null){//注册时验证用户名是否存在
            if(null != user){
                return new ResponseResult(-1,"当前用户名已被占用",user);
            }
        }else if(null != email && null != password){//登录验证
            if(null != user){
                return new ResponseResult(200,"登录成功",user);
            }else{
                return new ResponseResult(-1,"账号密码不对",user);
            }
        }
        if(StringHelper.isNullOrEmptyString(email)){
            return new ResponseResult(-1,"账号不能为空",user);
        }else{
            return new ResponseResult(200,"账号有效",user);
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
