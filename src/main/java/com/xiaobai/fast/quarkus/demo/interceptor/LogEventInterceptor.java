package com.xiaobai.fast.quarkus.demo.interceptor;

import jakarta.enterprise.event.Event;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.lang.reflect.Method;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/26
 * @since 1.0
 */
@LogEvent
@Interceptor
public class LogEventInterceptor {

    @AroundInvoke
    public Object logEvent(InvocationContext ctx) throws Exception {
        Method method = ctx.getMethod();
        Object[] parameters = ctx.getParameters();
        System.out.println("LOG INCEPTOR "+method.getName());
        return ctx.proceed();
    }
}
