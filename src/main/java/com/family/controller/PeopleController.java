package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.family.entity.Hall;
import com.family.entity.People;
import com.family.enums.ResultCode;
import com.family.service.impl.HallServiceImpl;
import com.family.service.impl.PeopleServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author test
 * @since 2019-02-12
 */
@RestController
@RequestMapping("/people")
@Api(description = "人员")
public class PeopleController {
    @Autowired
    PeopleServiceImpl peopleService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "人员查询")
    public ResponseResult getSurname(People people) {
        QueryWrapper<People> wrapper = new QueryWrapper<>();
        wrapper.setEntity(people);
        people = peopleService.getOne(wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), people);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    @ApiOperation(value = "分页查询人员")
    public IPage<People> getSurnames(People people) {
        IPage<People> page = new Page<>();
        return peopleService.page(page);
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "人员新增")
    public ResponseResult addSurname(People people) {
        people.setId(Common.getId());
        boolean flag = peopleService.save(people);
        if (flag) {
            return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), people);
        } else {
            return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), people);
        }
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "人员修改")
    public ResponseResult modifySurname(@RequestBody People people) {
        boolean flag = peopleService.updateById(people);
        if (flag) {
            return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), people);
        } else {
            return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), people);
        }
    }

}
