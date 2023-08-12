package com.xiaobai.fast.quarkus.seurity;

import java.security.BasicPermission;
import java.security.Permission;
import java.util.List;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class CustomPermission extends BasicPermission {

    private  List<String> permissions;

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    /**
     * Creates a new {@code BasicPermission} object with the specified name.
     * The name is the symbolic name of the {@code BasicPermission}, and the
     * actions {@code String} is currently unused.
     *
     * @param name    the name of the {@code BasicPermission}.
     * @param actions ignored.
     * @throws NullPointerException     if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */
    public CustomPermission(String name, String actions) {
        super(name, actions);
    }

    /**
     * Checks if the specified permission is "implied" by
     * this object.
     * <p>
     * More specifically, this method returns {@code true} if:
     * <ul>
     * <li> {@code p}'s class is the same as this object's class, and
     * <li> {@code p}'s name equals or (in the case of wildcards)
     *      is implied by this object's
     *      name. For example, "a.b.*" implies "a.b.c".
     * </ul>
     *
     * @param p the permission to check against.
     * @return {@code true} if the passed permission is equal to or
     * implied by this permission, {@code false} otherwise.
     */
    @Override
    public boolean implies(Permission p) {
        return permissions.contains(p.getName());
    }
}
