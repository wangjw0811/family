package com.family.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

public class Common {

    /**
     * 系统主键暂时采用UUID
     * @return
     */
    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 随机生成汉字
     * @param len 生成随机汉字个数
     * @return
     */
    public static String getChineseCharacter(int len){
        String ret="";
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try{
                str = new String(b, "GBk"); //转成中文
            }catch (UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
            ret+=str;
        }
        return ret;
    }
}
