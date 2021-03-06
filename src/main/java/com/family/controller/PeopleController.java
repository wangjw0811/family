package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.family.entity.People;
import com.family.entity.User;
import com.family.enums.ResultCode;
import com.family.service.impl.PeopleServiceImpl;
import com.family.service.impl.UserServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author test
 * @since 2019-02-12
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(description = "人员")
public class PeopleController {
    @Autowired
    PeopleServiceImpl peopleService;
    @Autowired
    UserServiceImpl userService;
    @GetMapping(value = "/people")
    @ResponseBody
    @ApiOperation(value = "人员查询")
    public ResponseResult getPeople(People people) {
        QueryWrapper<People> wrapper = new QueryWrapper<>();
        wrapper.setEntity(people);
        List<People> list = peopleService.list(wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), list);
    }

    @GetMapping(value = "/peoples")
    @ResponseBody
    @ApiOperation(value = "分页查询人员")
    public ResponseResult getPeoples(People people, HttpServletRequest request) {
        String current  = request.getParameter("page");
        String pageSize = request.getParameter("limit");
        IPage<People> page = new Page<>();
        page.setSize(Long.parseLong(pageSize));
        page.setCurrent(Long.parseLong(current));
        QueryWrapper<People> wrapper = new QueryWrapper<>();
        wrapper.setEntity(people);
        wrapper.orderByAsc("generation","birth_day");
        IPage<Map<String, Object>> mapIPage = peopleService.pageMaps(page, wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), mapIPage);
    }

    @PostMapping(value = "/addPeople")
    @ResponseBody
    @ApiOperation(value = "人员新增")
    public ResponseResult addPeople(@RequestBody People people) {
        people.setId(Common.getId());
        boolean flag = peopleService.save(people);
        if (flag) {
            return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), people);
        } else {
            return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), people);
        }
    }

    @PostMapping(value = "/modifyPeople")
    @ResponseBody
    @ApiOperation(value = "人员修改")
    @Transactional
    public ResponseResult modifyPeople(@RequestBody People people) {
        boolean flag = peopleService.updateById(people);
        people = peopleService.getById(people.getId());
        if (flag) {
            //修改成功后检查身份号信息及账号信息
            String idNumber = people.getIdNumber();
            if(!StringHelper.isNullOrEmptyString(idNumber)){
                //检查该身份证对应的账号是否存在
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.select("id_number",idNumber);
                List<User> list = userService.list(wrapper);
                if(list.size() == 0){
                    //账号不存在则创建创号
                    User user = new User();
                    user.setIdNumber(Common.md5(idNumber));
                    user.setPeopleId(people.getId());
                    //默认取名作为登录的用户名
                    user.setName(people.getForename());
                    //默认密码截取身份证后六位进行MD5加密
                    user.setPassword(Common.md5(idNumber.substring(idNumber.trim().length()-6)));
                    flag = userService.save(user);
                    if(flag){
                        log.error("人员信息修改成功，并成功创建账号");
                        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), people);
                    }else{
                        log.error("创建账号失败");
                        return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), people);
                    }
                }else{
                    log.info(people.getId() + "已存在账号");
                }
            }
            log.info("人员信息修改成功");
            return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), people);
        } else {
            log.error("人员信息修改失败");
            return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), people);
        }
    }
}
