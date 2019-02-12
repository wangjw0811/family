package com.family.utils;

import com.family.enums.IndexEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumUtil {
    /**
     *根据枚举值，获取说明
     * @param <T>
     * @param code
     * @param enumStatus
     * @return
     */
    public static <T extends IndexEnum> String getEnum(Integer code , Class<T> enumStatus){
        //循环遍历你的枚举类中的所有code值，相等则返回给调用者
        for (T each:enumStatus.getEnumConstants()) {
            if (code.equals(each.getIndex())){
                return each.getMessage();
            }
        }
        return null;
    }

    /**
     * 获取枚举选项列表
     */
    public  <T extends IndexEnum> List getEnums(Class<T> enumStatus){
        //循环遍历你的枚举类中的所有code值，相等则返回给调用者
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for (T each:enumStatus.getEnumConstants()) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value",each.getIndex());
            map.put("name",each.getMessage());
            list.add(map);
        }
        return list;
    }
}
