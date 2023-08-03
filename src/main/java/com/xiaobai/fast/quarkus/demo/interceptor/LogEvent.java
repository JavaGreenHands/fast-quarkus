package com.xiaobai.fast.quarkus.demo.interceptor;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/26
 * @since 1.0
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface LogEvent {
}
