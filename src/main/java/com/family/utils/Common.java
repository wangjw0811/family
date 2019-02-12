package com.family.utils;

import java.util.UUID;

public class Common {

    /**
     * 系统主键暂时采用UUID
     * @return
     */
    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
