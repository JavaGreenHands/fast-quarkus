package com.xiaobai.fast.quarkus.core.response;


import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

/**
 * 通用返回实体
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
@Schema(name = "R",description = "通用返回实体")
public class R implements Serializable {

    @Schema(name = "code",description = "状态码 0-正常 其他异常")
    private Integer code;
    @Schema(name = "msg",description = "消息")
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


    public static R ok(){
        return new R(ServiceCodeEnum.SUCCESS.getCode(), ServiceCodeEnum.SUCCESS.getMessage());
    }

}
