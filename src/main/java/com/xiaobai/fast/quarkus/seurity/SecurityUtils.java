package com.xiaobai.fast.quarkus.seurity;

import com.xiaobai.fast.quarkus.config.Constants;
import com.xiaobai.fast.quarkus.core.util.JsonUtils;
import com.xiaobai.fast.quarkus.system.domain.SysUser;
import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.core.ResteasyContext;

import java.security.Principal;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class SecurityUtils {
    /**
     * 获取用户信息
     *
     * @return 用户信息字符串
     */
    public static String getJwtUserInfo() {
        SecurityContext contextData = ResteasyContext.getContextData(SecurityContext.class);
        Principal userPrincipal = contextData.getUserPrincipal();
        if (userPrincipal instanceof JsonWebToken) {
            Object claim = JsonWebToken.class.cast(userPrincipal).getClaim(Constants.ClaimName);
            if (claim == null) {
                throw new UnauthorizedException();
            }
            return claim.toString();
        } else {
            throw new UnauthorizedException();
        }

    }

    /**
     * 获取用户信息
     * @return sysUser
     */
    public static SysUser getUserInfo() {
        String userInfo = getJwtUserInfo();
        return JsonUtils.parse(userInfo, SysUser.class);
    }
}
