package com.xiaobai.fast.quarkus.core.response;

import com.xiaobai.fast.quarkus.core.ienum.ServiceCodeEnum;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class R {
    private Integer code;
    private String msg;


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


    public static R error(Integer code,String message){
        return new R(code,message);
    }
    public static R error(String message){
        return new R(ServiceCodeEnum.ERROR.getCode(),message);
    }
    public static R successBody(){
        return new R();
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


    public R() {
    }
}
