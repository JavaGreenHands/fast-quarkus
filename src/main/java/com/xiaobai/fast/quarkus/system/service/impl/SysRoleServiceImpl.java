package com.xiaobai.fast.quarkus.system.service.impl;

import com.xiaobai.fast.quarkus.system.service.SysRoleService;
import com.xiaobai.fast.quarkus.utils.CollectionUtils;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.Set;

import static com.xiaobai.fast.quarkus.config.Constants.ADMIN_PERMISSION_KEY;
import static com.xiaobai.fast.quarkus.config.Constants.ADMIN_ROLE_KEY;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class SysRoleServiceImpl implements SysRoleService {
    /**
     * 根据角色返回所有的权限
     *
     * @param roles 角色列表
     * @return 权限列表
     */
    @Override
    public Set<String> getPermissionByRole(Set<String> roles) {
        if(CollectionUtils.isEmpty(roles)){
            return Collections.emptySet();
        }
        if(roles.contains(ADMIN_ROLE_KEY)){
            return Collections.singleton(ADMIN_PERMISSION_KEY);
        }
//        SysRole.find("")
        return Collections.singleton("access");
    }
}
