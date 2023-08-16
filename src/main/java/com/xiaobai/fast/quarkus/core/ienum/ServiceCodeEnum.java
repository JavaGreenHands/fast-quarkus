package com.xiaobai.fast.quarkus.core.ienum;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public enum ServiceCodeEnum {
   SUCCESS(0,"SUCCESS"),
   ERROR(600,"ERROR"),

    PARAMETER_ERROR(601,"参数错误,请检查参数"),
    UPLOAD_ERROR(602,"文件上传错误！"),


    /**
     * 框架层面错误
     */
    BAD_REQUEST(400,"请求方式错误"),

    ;
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ServiceCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
