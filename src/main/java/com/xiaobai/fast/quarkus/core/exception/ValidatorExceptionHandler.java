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
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import java.util.stream.Collectors;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
@Provider
public class ValidatorExceptionHandler implements ExceptionMapper<ResteasyViolationException> {
    @Override
    public Response toResponse(ResteasyViolationException exception) {

        // hibernate validate 校验错误
         if(exception instanceof ConstraintViolationException){
            String messages = ConstraintViolationException.class.cast(exception)
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
        }
         // 未定义异常
         else {
             R<Object> error = R.error(exception.getMessage());
             // 处理其他异常
             return Response.status(Response.Status.OK)
                     .header("content-type", MediaType.APPLICATION_JSON)
                     .entity(JsonUtils.object2Json(error))
                     .build();
         }
    }
}
