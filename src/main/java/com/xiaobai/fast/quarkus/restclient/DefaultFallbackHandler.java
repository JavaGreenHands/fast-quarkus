package com.xiaobai.fast.quarkus.restclient;

import com.xiaobai.fast.quarkus.core.util.ExceptionUtils;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class DefaultFallbackHandler implements FallbackHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultFallbackHandler.class);
    @Override
    public String handle(ExecutionContext executionContext) {
        Throwable failure = executionContext.getFailure();
        String exception = ExceptionUtils.getException(failure);
        Method method = executionContext.getMethod();
        Object[] parameters = executionContext.getParameters();
        logger.error("method:{},parameters:{}，调用异常:{},",method,parameters,exception);
        return "error access";
    }
}
