package com.xiaobai.fast.quarkus.utils;

import java.util.UUID;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class IdUtils {

    /**
     * 获取UUID
     * @return UUID
     */
    public static String getSimpleUUID(){

       return UUID.randomUUID().toString().replace("-","");

    }
}
