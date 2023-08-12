package com.xiaobai.fast.quarkus.core.util;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/20
 * @since 1.0
 */
public class TokenUtil {
    public static void main(String[] args) {
        String token =
                Jwt.issuer("fast-quarkus")
                        .upn("fast-quarkus")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin","Admin")))
                        .claim("userInfo", "{'userName':'aaa',password}")
                        .expiresAt(System.currentTimeMillis()+ 60 * 60 * 8 * 1000)
                        .sign();
        System.out.println(token);
    }

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    public static String createToken(Map<String,Object> userInfo){
       return
                Jwt.issuer("fast-quarkus")
                        .upn("fast-quarkus")
                        .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                        .claim("userInfo", JsonUtils.object2Json(userInfo))
                        .sign();
    }

    public static String getUserInfo(JsonWebToken jwt){
        return  jwt.getClaim("userInfo").toString();
    }
}
