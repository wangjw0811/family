package com.family.service.impl;


import com.family.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取枚举选项列表
 */
@Service
@Slf4j
public class EnumsServiceImpl {
    public Map getEnums(String enums) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] es = enums.split(",");
        for (String e : es) {
            EnumUtil enumUtil = new EnumUtil();
            String className = "com.csjscm.core.suppermark.enums." + e;
            Class clzz = null;
            try {
                clzz = Class.forName(className);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            map.put(e, enumUtil.getEnums(clzz));
            log.info("枚举类" + className + "返回值：" + enums);
        }
        return map;
    }

}
