package com.xiaobai.fast.quarkus.core.interceptor;

import com.xiaobai.fast.quarkus.core.exception.ServiceException;
import com.xiaobai.fast.quarkus.core.util.ExceptionUtils;
import com.xiaobai.fast.quarkus.core.util.JsonUtils;
import com.xiaobai.fast.quarkus.system.domain.AuditLog;
import com.xiaobai.fast.quarkus.system.repository.AuditLogRepository;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ResourceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@LogEvent
@Priority(1)
@Interceptor
public class LogEventInterceptor {
    private static Logger logger = LoggerFactory.getLogger(LogEventInterceptor.class);
    @Inject
    ResourceInfo resourceInfo;
    @Inject
    AuditLogRepository auditLogRepository;

    @AroundInvoke
    public Object logEvent(InvocationContext ctx) throws Exception {
        Object[] parameters = ctx.getParameters();
        AuditLog auditLog = new AuditLog();
        long start = System.currentTimeMillis();
        try {
            LogEvent logEventAnnotation = ctx.getMethod().getAnnotation(LogEvent.class);
            String logTitle = logEventAnnotation.value();

            auditLog.setLogTitle(logTitle);
            Method resourceMethod = resourceInfo.getResourceMethod();
            Path path = resourceMethod.getAnnotation(Path.class);
            String httpMethod =getHttpMethod(resourceMethod);
            // 请求的url
            String url = path.value();
            auditLog.setUrl(url);
            auditLog.setRequestType(httpMethod);
            auditLog.setRequestParam(getRequestParam(resourceMethod,parameters));
        } catch (Exception e) {
            logger.error("获取日志记录错误");
        }

        try {
            Object proceed = ctx.proceed();
            long end = System.currentTimeMillis();
            auditLog.setCostTime(end-start);
            auditLog.setFailed(0);
            return  proceed;

        } catch (Exception e) {
            long end = System.currentTimeMillis();
            auditLog.setCostTime(end-start);
            auditLog.setFailed(1);
            logger.error("方法调用错误:", e);
            auditLog.setErrorMsg(ExceptionUtils.getException(e));
            if(e instanceof ServiceException serviceException){
                throw new ServiceException(serviceException);
            }else {
                throw new Exception(e);

            }
        } finally {
            try {
                auditLogRepository.saveAuditLog(auditLog);
            } catch (Exception ex) {
                logger.error("保存日志错误");
            }
        }
    }

    private String getHttpMethod(Method resourceMethod) {
        GET annotation = resourceMethod.getAnnotation(GET.class);
        if(annotation != null){
            return "GET";
        }
        PUT put = resourceMethod.getAnnotation(PUT.class);
        if(put != null){
            return "PUT";
        }
        POST post = resourceMethod.getAnnotation(POST.class);
        if(post != null){
            return "POST";
        }
        DELETE delete = resourceMethod.getAnnotation(DELETE.class);
        if(delete != null){
            return "DELETE";
        }
        PATCH patch = resourceMethod.getAnnotation(PATCH.class);
        if(patch != null){
            return "PATCH";
        }
       return "UNKNOWN";

    }

    private String getRequestParam(Method resourceMethod, Object[] args) {
        Parameter[] parameters = resourceMethod.getParameters();

        List<Object> argList = new ArrayList<>();
       Map<String,Object> pathParam = new HashMap<>();
       Map<String,Object> queryParamMap = new HashMap<>();
       Map<String,Object> otherMap = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            PathParam annotation = parameters[i].getAnnotation(PathParam.class);
            if (annotation != null) {
                String value = annotation.value();
                pathParam.put(value,args[i]);
            }
            QueryParam queryParam = parameters[i].getAnnotation(QueryParam.class);
            if (queryParam != null) {
                String key = queryParam.value();
                Object value = args[i] ;
                queryParamMap.put(key,value);
            }
            try {
                otherMap.put("json",args[i]);
            }catch (Exception e){
                logger.error("参数转换错误");
            }

        }
        argList.add(pathParam);
        argList.add(queryParamMap);
        argList.add(otherMap);
        try {
            return JsonUtils.object2Json(argList);

        }catch (Exception e){
            logger.error("转换json错误",e);
        }
        return "Parse json error";
    }
}
