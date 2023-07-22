package com.xiaobai.fast.quarkus.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class JsonUtils {
   final static ObjectMapper mapper = new ObjectMapper();

    /**
     * java 对象转JSON
     * @param obj
     * @return
     */
   public static String object2Json(Object obj) {
       String value = null;
       try {
           value =   mapper.writeValueAsString(obj);
       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       }
       return value;
   }

}
