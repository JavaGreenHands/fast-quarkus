package com.xiaobai.fast.quarkus.core.exception;

import com.xiaobai.fast.quarkus.core.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.util.JsonUtils;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        if (throwable instanceof ServiceException) {
            ServiceException exception = ServiceException.class.cast(throwable);

            R<Object> error = R.error(exception.getCode(), exception.getMessage());
            String data = JsonUtils.object2Json(error);
            System.out.println(data);
            // 处理自定义异常
            return Response
                    .status(Response.Status.OK)
                    .header("content-type", MediaType.APPLICATION_JSON)
                    .entity(data)
                    .build();
        }else if(throwable instanceof BadRequestException){
            R<Object> error = R.error(ServiceCodeEnum.BAD_REQUEST.getCode(),ServiceCodeEnum.BAD_REQUEST.getMessage());
            // 处理自定义异常
            return Response
                    .status(Response.Status.OK)
                    .header("content-type", MediaType.APPLICATION_JSON)
                    .entity(JsonUtils.object2Json(error))
                    .build();
        }else if(throwable instanceof ConstraintViolationException){
            // validate 校验错误
            String messages = ConstraintViolationException.class.cast(throwable)
                    .getConstraintViolations()
                    .stream()
                    .map(cv -> cv.getMessage())
                    .collect(Collectors.joining(", "));
            R<Object> error = R.error(ServiceCodeEnum.PARAMETER_ERROR.getCode(),messages);
            // 处理自定义异常
            return Response
                    .status(Response.Status.OK)
                    .header("content-type", MediaType.APPLICATION_JSON)
                    .entity(JsonUtils.object2Json(error))
                    .build();
        }else if(throwable instanceof ForbiddenException){
         return    Response
                    .status(Response.Status.UNAUTHORIZED)
                    .header("content-type", MediaType.APPLICATION_JSON)
                    .entity(JsonUtils.object2Json(throwable))
                    .build();
        }
        else {
            R<Object> error = R.error(throwable.getMessage());
            // 处理其他异常
            return Response.status(Response.Status.OK)
                    .header("content-type", MediaType.APPLICATION_JSON)
                    .entity(JsonUtils.object2Json(error))
                    .build();
        }
    }
}
