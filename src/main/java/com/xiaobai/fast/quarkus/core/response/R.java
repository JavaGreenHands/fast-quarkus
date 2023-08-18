package com.xiaobai.fast.quarkus.core.response;


import java.io.Serializable;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class R implements Serializable {
    private Integer code;
    private String msg;


    public R(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public R() {
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



}
