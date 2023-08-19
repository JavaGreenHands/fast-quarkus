package com.xiaobai.fast.quarkus.system.repository;

import com.xiaobai.fast.quarkus.system.domain.SysRole;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class SysRoleRepository implements PanacheRepository<SysRole> {
}
