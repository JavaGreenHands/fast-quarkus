package com.xiaobai.fast.quarkus.system.domain.vo;

import jakarta.validation.constraints.NotEmpty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Schema(name = "登录实体")
public class LoginVo {

    @SchemaProperty(name = "用户名称")
    @NotEmpty(message = "用户名称不能为空")
    private String username;
    @SchemaProperty(name = "用户密码")
    @NotEmpty(message = "用户密码不能为空")
    private String password;

    @SchemaProperty(name = "toekn",hidden = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public LoginVo() {
    }

    public LoginVo( String token) {

        this.token = token;
    }
}
