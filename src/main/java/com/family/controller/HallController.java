package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.family.entity.Hall;
import com.family.entity.User;
import com.family.enums.ResultCode;
import com.family.service.impl.HallServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2019-02-12
 */
@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    HallServiceImpl hallService;
    @GetMapping
    @ResponseBody
    @ApiOperation(value = "姓氏查询")
    public ResponseResult getSurname(Hall hall){
        QueryWrapper<Hall> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hall);
        hall = hallService.getOne(wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(),ResultCode.SUCCESS.getMessage(),hall);
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "姓氏新增")
    public ResponseResult addSurname(Hall hall){
        hall.setId(Common.getId());
        boolean flag = hallService.save(hall);
        if(flag){
            return new ResponseResult(ResultCode.SUCCESS.getIndex(),ResultCode.SUCCESS.getMessage(),hall);
        }else{
            return new ResponseResult(ResultCode.FAILURE.getIndex(),ResultCode.FAILURE.getMessage(),hall);
        }
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "姓氏修改")
    public ResponseResult modifySurname(@RequestBody Hall hall){
        boolean flag = hallService.updateById(hall);
        if(flag){
            return new ResponseResult(ResultCode.SUCCESS.getIndex(),ResultCode.SUCCESS.getMessage(),hall);
        }else{
            return new ResponseResult(ResultCode.FAILURE.getIndex(),ResultCode.FAILURE.getMessage(),hall);
        }
    }


}
