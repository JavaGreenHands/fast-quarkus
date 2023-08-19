package com.xiaobai.fast.quarkus.core.exception;

import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class ServiceException extends RuntimeException{
    private static final Logger log = LoggerFactory.getLogger(ServiceException.class);

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ServiceException() {
    }
    public ServiceException(RuntimeException runtimeException) {
        log.error("ServiceException：",runtimeException);
        if(runtimeException instanceof ServiceException serviceException){
            this.code  = serviceException.getCode();
            this.message  = serviceException.getMessage();
        }
    }


    private Integer code;
    private String message;
    public ServiceException(String message) {
        super(message);
        this.code = ServiceCodeEnum.ERROR.getCode();
        this.message = message;
    }

    public ServiceException(Integer code,String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public ServiceException(ServiceCodeEnum code, ServiceCodeEnum message) {
        super(message.getMessage());
        this.code = code.getCode();
        this.message = message.getMessage();
    }
    public ServiceException(ServiceCodeEnum code, ServiceCodeEnum message,Object... args) {
        super(message.getMessage());
        String msg = "";
        msg =  message.getMessage() == null ? "":String.format(message.getMessage(),args);
        this.code = code.getCode();
        this.message =msg;
    }
    public ServiceException(Integer code,String message,Object... args) {
        super(message);
        message =  message == null ? "":String.format(message,args);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
