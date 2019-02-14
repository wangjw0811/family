package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.family.entity.Hall;
import com.family.entity.User;
import com.family.enums.ResultCode;
import com.family.service.impl.HallServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
@RequestMapping("/api")
@Api(description = "姓氏")
@Slf4j
public class HallController {
    @Autowired
    HallServiceImpl hallService;
    @GetMapping(value="/hall")
    @ResponseBody
    @ApiOperation(value = "姓氏查询")
    public ResponseResult getSurname(Hall hall){
        QueryWrapper<Hall> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hall);
        hall = hallService.getOne(wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(),ResultCode.SUCCESS.getMessage(),hall);
    }

    @GetMapping(value="/halls")
    @ResponseBody
    @ApiOperation(value = "分页查询姓氏")
    public String getSurnames(Hall hall){
        IPage<Hall> page = new Page<>(1,10);
        QueryWrapper<Hall> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hall);
        IPage<Map<String, Object>> mapIPage = hallService.pageMaps(page, wrapper);
        String result = ResponseResult.returnData(mapIPage);
        log.info(result);
        return result;
    }

    @PutMapping(value="/hall")
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

    @PostMapping(value="/hall")
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
