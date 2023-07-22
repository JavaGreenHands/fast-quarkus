package com.xiaobai.fast.quarkus.demo.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
public class Demo {

    /**
     * 用户名称
     */
    @NotEmpty(message = " username not empty")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = " password not empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
