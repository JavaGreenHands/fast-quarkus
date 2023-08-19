package com.xiaobai.fast.quarkus.core.interceptor;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.util.Nonbinding;
import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.*;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/26
 * @since 1.0
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Inherited
public @interface LogEvent {
    /**
     *
     * @return 日志标题
     */
    @Nonbinding
    String value() default "";


}
