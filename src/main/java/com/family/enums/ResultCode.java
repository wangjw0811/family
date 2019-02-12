package com.family.enums;

import lombok.Getter;

@Getter
public enum ResultCode implements IndexEnum{
    SUCCESS("请求成功",200),
    FAILURE("请求失败",999),
    ;
    private Integer index;
    private String message;
    ResultCode(String message,Integer index) {
        this.index = index;
        this.message = message;
    }
}
