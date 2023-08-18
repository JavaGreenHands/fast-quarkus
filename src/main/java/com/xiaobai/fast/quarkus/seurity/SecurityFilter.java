package com.xiaobai.fast.quarkus.seurity;

import com.xiaobai.fast.quarkus.config.Constants;
import com.xiaobai.fast.quarkus.utils.CollectionUtils;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.auth.principal.DefaultJWTParser;
import io.smallrye.jwt.auth.principal.JWTAuthContextInfo;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.core.ResteasyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 拦截所有的未添加身份校验的api
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
//@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);
    @Inject
    ResourceInfo resourceInfo;

    @ConfigProperty(name = "mp.jwt.verify.publickey.location")
    private String keyLocation;
    @Override
    public void filter(ContainerRequestContext rc) {
        final Class declaring = resourceInfo.getResourceClass();
        final Method method = resourceInfo.getResourceMethod();

        if (declaring == null || method == null)
            return;

        String[] rolesAllowed = null;
        boolean denyAll;
        boolean permitAll;
        RolesAllowed allowed = (RolesAllowed) declaring.getAnnotation(RolesAllowed.class);
        RolesAllowed methodAllowed = method.getAnnotation(RolesAllowed.class);
        if (methodAllowed != null)
            allowed = methodAllowed;
        if (allowed != null) {
            rolesAllowed = allowed.value();
        }

        denyAll = (declaring.isAnnotationPresent(DenyAll.class)
                && method.isAnnotationPresent(RolesAllowed.class) == false
                && method.isAnnotationPresent(PermitAll.class) == false) || method.isAnnotationPresent(DenyAll.class);

        permitAll = (declaring.isAnnotationPresent(PermitAll.class) == true
                && method.isAnnotationPresent(RolesAllowed.class) == false
                && method.isAnnotationPresent(DenyAll.class) == false) || method.isAnnotationPresent(PermitAll.class);
        // 所有用户禁止访问
        if (denyAll) {
            throw new ForbiddenException();
        }
        // 放行
        if (permitAll) {
            return;
        }
        boolean isContains = rc.getHeaders().containsKey(Constants.Authentication);
        if (!isContains) {
            throw new UnauthorizedException();
        }
        MultivaluedMap<String, String> headers = rc.getHeaders();
        List<String> strings = headers.get(Constants.Authentication);
        if (CollectionUtils.isEmpty(strings)) {
            throw new UnauthorizedException();

        }
        String token = strings.get(0).replace("Bearer ", "");
        log.debug("token:{}", token);
        try {
            DefaultJWTParser defaultJWTParser = new DefaultJWTParser(new JWTAuthContextInfo(keyLocation,Constants.JWT_ISSUER));
            JsonWebToken parse = defaultJWTParser.parse(token);
            Set<String> groups = parse.getGroups();
            rc.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return parse;
                }

                @Override
                public boolean isUserInRole(String role) {
                    return !groups.contains(Constants.ADMIN_ROLE_KEY) &&groups.contains(role);
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return "basic";
                }
            });

            if (rolesAllowed == null && !denyAll && !permitAll &&  parse.getClaim(Constants.ClaimName) == null) {
                throw new UnauthorizedException();
            }
        } catch (ParseException e) {
            log.error("parse exception", e);
            throw new UnauthorizedException();
        }

    }

}

