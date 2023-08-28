package com.xiaobai.fast.quarkus.system.domain.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Schema(name = "登录实体")
public class LoginVo {

    @Schema(name = "username",description = "用户名称")
    @NotEmpty(message = "用户名称不能为空")
    @QueryParam("username")
    private String username;
    @Schema(name = "password",description = "用户密码")
    @NotEmpty(message = "用户密码不能为空")
    @QueryParam("password")
    private String password;

    @Schema(name = "token",hidden = true)
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
