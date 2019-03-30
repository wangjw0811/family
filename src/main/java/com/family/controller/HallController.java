package com.family.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.family.entity.Hall;
import com.family.entity.vo.PeopleVO;
import com.family.enums.ResultCode;
import com.family.service.impl.HallServiceImpl;
import com.family.utils.Common;
import com.family.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @GetMapping(value = "/getHallById")
    @ResponseBody
    @ApiOperation(value = "姓氏查询")
    public ResponseResult getHallById(@RequestParam String id) {
        Hall hall = hallService.getById(id);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), hall);
    }

    @GetMapping(value = "/getSenioritiesById")
    @ResponseBody
    @ApiOperation(value = "辈分集查询")
    public ResponseResult getSenioritiesById(@RequestParam String id) {
        Hall hall = hallService.getById(id);
        String seniorities = hall.getSeniorities();
        List<PeopleVO> peoples = new ArrayList<>();
        if(!StringHelper.isNullOrEmptyString(seniorities)){
            String[] senioritie = seniorities.split(",");
            for(int i = 1;i<=senioritie.length;i++){
                PeopleVO people = PeopleVO.builder()
                        .generation(i)
                        .generationDesc("第"+Common.arabicToChinese(i)+"代("+senioritie[i-1]+")")
                        .build();
                peoples.add(people);
            }
        }
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), peoples);
    }

    @GetMapping(value = "/hall")
    @ResponseBody
    @ApiOperation(value = "姓氏查询")
    public ResponseResult getHall() {
        List<Hall> list = hallService.list();
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), list);
    }


    @GetMapping(value = "/halls")
    @ResponseBody
    @ApiOperation(value = "分页查询姓氏")
    public ResponseResult getHalls(Hall hall) {
        IPage<Hall> page = new Page<>(1, 10);
        QueryWrapper<Hall> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hall);
        IPage<Hall> mapIPage = hallService.page(page, wrapper);
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), mapIPage);
    }


    @PostMapping(value = "/addHall")
    @ResponseBody
    @ApiOperation(value = "姓氏新增")
    public ResponseResult addHall(@RequestBody Hall hall) {
        hall.setId(Common.getId());
        boolean flag = hallService.save(hall);
        if (flag) {
            return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), hall);
        } else {
            return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), hall);
        }
    }

    @PostMapping(value = "/modifyHall")
    @ResponseBody
    @ApiOperation(value = "姓氏修改")
    public ResponseResult modifyHall(@RequestBody Hall hall) {
        boolean flag = hallService.updateById(hall);
        if (flag) {
            return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(), hall);
        } else {
            return new ResponseResult(ResultCode.FAILURE.getIndex(), ResultCode.FAILURE.getMessage(), hall);
        }
    }
}
