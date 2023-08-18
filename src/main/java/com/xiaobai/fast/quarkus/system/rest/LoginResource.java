package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.system.domain.vo.LoginVo;
import com.xiaobai.fast.quarkus.system.service.LoginService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/login")
public class LoginResource {

    @Inject
    LoginService loginService;

    @POST
    @Path("")
    @PermitAll
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Operation(summary = "登录接口")
    public LoginVo login(@Valid LoginVo loginVo){
        String token = loginService.login(loginVo);
        return new LoginVo(token);
    }
}
