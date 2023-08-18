package com.xiaobai.fast.quarkus.core.exception;

import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.response.R;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import java.util.stream.Collectors;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Provider
public class ValidatorExceptionHandler implements ExceptionMapper<ResteasyViolationException> {
    @Override
    public Response toResponse(ResteasyViolationException exception) {

        // hibernate validate 校验错误
        String messages = ConstraintViolationException.class.cast(exception)
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        R error =new R(ServiceCodeEnum.PARAMETER_ERROR.getCode(), messages);
        // 处理自定义异常
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(error)
                .build();

    }
}
