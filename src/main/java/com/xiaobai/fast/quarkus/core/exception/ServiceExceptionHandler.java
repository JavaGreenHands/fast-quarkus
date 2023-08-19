package com.xiaobai.fast.quarkus.core.exception;

import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.util.JsonUtils;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {
    private static final Logger log = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    /**
     * Map an exception to a {@link Response}. Returning {@code null} results in a
     * {@link Response.Status#NO_CONTENT} response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     *
     * @param exception the exception to map to a response.
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(ServiceException exception) {
        log.error("ServiceExceptionHandler:",exception);
        R error = new R(exception.getCode(), exception.getMessage());
        String data = JsonUtils.object2Json(error);
        // 处理自定义异常x
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("content-type", MediaType.APPLICATION_JSON)
                .entity(data)
                .build();
    }
}
