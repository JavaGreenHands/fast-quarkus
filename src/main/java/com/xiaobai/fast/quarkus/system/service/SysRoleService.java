package com.xiaobai.fast.quarkus.system.service;

import jakarta.decorator.Decorator;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;

/**
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public interface SysRoleService {

    /**
     * 根据角色返回所有的权限
     * @param roles
     * @return
     */
    Set<String> getPermissionByRole(Set<String> roles);
}
