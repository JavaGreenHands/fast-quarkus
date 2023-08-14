package com.xiaobai.fast.quarkus.seurity;

import io.quarkus.security.StringPermission;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class PermissionsIdentityAugmentor implements SecurityIdentityAugmentor {
    /**
     * Augments a security identity to allow for modification of the underlying identity.
     *
     * @param identity The identity
     * @param context
     * @return A completion stage that will resolve to the modified identity
     */
    @Override
    public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
        if (isNotAdmin(identity)) {
            return Uni.createFrom().item(identity);
        }
        return Uni.createFrom().item(build(identity));
    }
    private boolean isNotAdmin(SecurityIdentity identity) {
        return identity.isAnonymous() || !"admin".equals(identity.getPrincipal().getName());
    }

    SecurityIdentity build(SecurityIdentity identity) {
        Set<String> roles = identity.getRoles();
        List<String> permissionList =new ArrayList<>();
        permissionList.add("access");
        CustomPermission customPermission = new CustomPermission("CustomPermission", "CustomActions");
        customPermission.setPermissions(permissionList);
        // 根据roes 获取权限
        return QuarkusSecurityIdentity.builder()
                .addRoles(identity.getRoles())
                .setPrincipal(identity.getPrincipal())
                .setAnonymous(identity.isAnonymous())
                .addAttributes(identity.getAttributes())
                .addPermissionChecker(new Function<Permission, Uni<Boolean>>() {
                    @Override
                    public Uni<Boolean> apply(Permission requiredPermission) {
                        boolean accessGranted;
                        if (customPermission.implies(requiredPermission)) {
                            accessGranted = true;
                        }else {
                            accessGranted = false;
                        }
                        return Uni.createFrom().item(accessGranted);
                    }
                })
                .build();
    }
}
