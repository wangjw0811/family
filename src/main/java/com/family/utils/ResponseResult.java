package com.family.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.family.enums.ResultCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseResult {
    private Integer resultCode;
    private String resultMsg;
    private Object data;

    public ResponseResult(Object data) {
        this.data = data;
    }

    public ResponseResult(Integer resultCode, String resultMsg, Object data){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public static String returnData(IPage iPage){
        Map map = new HashMap();
        map.put("data",iPage.getRecords());
        map.put("total",iPage.getTotal());
        map.put("resultCode",ResultCode.SUCCESS.getIndex());
        map.put("resultMsg",ResultCode.SUCCESS.getMessage());
        String result = JSONObject.toJSONString(map);
        return result;
    }
}
