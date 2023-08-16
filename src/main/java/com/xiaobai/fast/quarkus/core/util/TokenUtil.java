package com.xiaobai.fast.quarkus.core.util;

import com.xiaobai.fast.quarkus.config.Constants;
import com.xiaobai.fast.quarkus.system.domain.SysRole;
import com.xiaobai.fast.quarkus.system.domain.SysUser;
import com.xiaobai.fast.quarkus.utils.CollectionUtils;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jose4j.json.internal.json_simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

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

    public static String createToken(SysUser sysUser) {
        Set<String> roles = new HashSet<>(Collections.emptySet());
        Set<SysRole> sysRoleSet = sysUser.getSysRoleSet();
        if(!CollectionUtils.isEmpty(sysRoleSet)){
            Set<String> collect = sysRoleSet.stream().map(SysRole::getRoleNameKey).collect(Collectors.toSet());
            roles.addAll(collect);
        }
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("userId",sysUser.getUserId());
        userMap.put("username",sysUser.getUsername());
        return Jwt.issuer(Constants.JWT_ISSUER)
                .upn(Constants.JWT_UPN)
                .groups(roles)
                .claim("userInfo", JSONObject.toJSONString(userMap))
                .expiresAt(System.currentTimeMillis() + 60 * 60 * 8 * 1000)
                .sign();
    }
}
