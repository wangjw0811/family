package com.family.controller;

import com.family.enums.ResultCode;
import com.family.service.impl.EnumsServiceImpl;
import com.family.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "根据枚举类名获取数据字典")
@RequestMapping(value = "/api")
public class GetEnumController {
    @Autowired
    private EnumsServiceImpl enumsService;

    @GetMapping(value = "/enum")
    @ApiOperation(value = "获取数据选项")
    public ResponseResult getEnums(@RequestParam String enums) {
        return new ResponseResult(ResultCode.SUCCESS.getIndex(), ResultCode.SUCCESS.getMessage(),enumsService.getEnums(enums));
    }
}
