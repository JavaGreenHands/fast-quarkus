package com.xiaobai.fast.quarkus.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     * @param collection 集合
     * @return true 是空的
     * false 不为空
     */
    public static boolean isEmpty(Collection<?> collection){

        return collection == null || collection.isEmpty();
    }

    /**
     * 判断Map集合是否为空
     * @param map 参数
     * @return true 是空的
     *      * false 不为空
     */
    public static boolean isEmpty(Map<?,?> map){

        return map == null || map.isEmpty();
    }
}
