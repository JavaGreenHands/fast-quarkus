package com.xiaobai.fast.quarkus.core.response;

import com.xiaobai.fast.quarkus.core.ienum.ServiceCodeEnum;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class R<T> {
    private Integer code;
    private String msg;
    private T data;


    public R(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public R(Integer code) {
        this.code = code;
    }

    public R(String message) {
        this.msg = message;
    }

    public static <T> R<T> success(T data){
        return new R<>(ServiceCodeEnum.SUCCESS.getCode(),ServiceCodeEnum.SUCCESS.getMessage(),data);
    }
    public static  R success(){
        return new R<>(ServiceCodeEnum.SUCCESS.getCode(),ServiceCodeEnum.SUCCESS.getMessage(),null);
    }
    public static <T> R<T> success(String message,T data){
        return new R<>(ServiceCodeEnum.SUCCESS.getCode(),message,data);
    }
    public static <T> R<T> error(Integer code,String message){
        return new R<>(code,message);
    }
    public static <T> R<T> error(String message){
        return new R<>(ServiceCodeEnum.ERROR.getCode(),message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R() {
    }
}
