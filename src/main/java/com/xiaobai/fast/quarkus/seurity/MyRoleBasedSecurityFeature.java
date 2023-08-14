package com.xiaobai.fast.quarkus.seurity;

import com.xiaobai.fast.quarkus.core.RequestFilter;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.FeatureContext;
import org.jboss.resteasy.plugins.interceptors.RoleBasedSecurityFilter;

import java.lang.reflect.Method;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 14/8/2023
 * @since 1.0
 */
public class MyRoleBasedSecurityFeature implements DynamicFeature {
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext configurable) {
        @SuppressWarnings("rawtypes")
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

        if (rolesAllowed != null || denyAll || permitAll) {
            RoleBasedSecurityFilter filter = new RoleBasedSecurityFilter(rolesAllowed, denyAll, permitAll);
            configurable.register(filter);
        }else {
            RequestFilter filter = new RequestFilter(rolesAllowed, denyAll, permitAll);
            configurable.register(filter);

        }
    }
}
