package com.family.utils;

import lombok.Data;

@Data
public class ResponseResult {
    private Integer resultCode;
    private String resultMsg;
    private Object data;
    public ResponseResult(Integer resultCode,String resultMsg,Object data){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
}
