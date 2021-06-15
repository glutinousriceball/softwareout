package com.wjm.softwareout.system.utils;

import java.util.UUID;

/**
 * @ClassName RandomUtil
 * @Description TODO: 随机字符串工具。生成一个UUID
 *
 * @Date 2019/07/18 15:18
 * @Version 1.0
 **/
public class RandomUtil {

    /**
     * 功能描述: 生成一个随机UUID
     *
     * @Date 15:21 2019/07/18
     * @param
     * @return java.lang.String
     */
    public static String generateRandomUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
