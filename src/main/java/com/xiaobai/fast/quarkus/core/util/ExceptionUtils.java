package com.xiaobai.fast.quarkus.core.util;

import io.quarkus.runtime.util.ExceptionUtil;
import io.quarkus.runtime.util.StringUtil;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class ExceptionUtils {


    /**
     * 获取异常信息
     * @param throwable 异常
     * @return 异常信息
     */
    public static String getException(Throwable throwable){
        String cause = ExceptionUtil.generateStackTrace(throwable);
        if(StringUtil.isNullOrEmpty(cause)){
            return "NOT ERROR MSG";
        }
        return cause.length() > 2000 ? cause.substring(0,2000):cause;
    }
}
