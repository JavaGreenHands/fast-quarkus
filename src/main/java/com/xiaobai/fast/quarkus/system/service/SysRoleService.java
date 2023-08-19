package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.exception.ServiceException;
import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import com.xiaobai.fast.quarkus.system.domain.SysRole;
import com.xiaobai.fast.quarkus.system.domain.vo.RoleQueryVo;
import com.xiaobai.fast.quarkus.system.repository.SysRoleRepository;
import com.xiaobai.fast.quarkus.utils.CollectionUtils;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.xiaobai.fast.quarkus.config.Constants.ADMIN_PERMISSION_KEY;
import static com.xiaobai.fast.quarkus.config.Constants.ADMIN_ROLE_KEY;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class SysRoleService {
    @Inject
    SysRoleRepository sysRoleRepository;

    /**
     * 根据角色返回所有的权限
     *
     * @param roles 角色列表
     * @return 权限列表
     */
    public Set<String> getPermissionByRole(Set<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptySet();
        }
        if (roles.contains(ADMIN_ROLE_KEY)) {
            return Collections.singleton(ADMIN_PERMISSION_KEY);
        }
        return sysRoleRepository.find(" FROM SysRole where isDel=0 AND roleKey in (:roles)", roles).stream()
                .flatMap(x -> x.getMenus().stream())
                .map(SysMenu::getPermissionString)
                .filter(x -> !StringUtil.isNullOrEmpty(x))
                .collect(Collectors.toSet());
    }

    @Transactional
    public void addRole(SysRole sysRole) {
        PanacheQuery<SysRole> sysRolePanacheQuery = sysRoleRepository.find("FROM SysRole where isDel=0 AND (roleName =?1 or roleKey=?2)", sysRole.getRoleName(), sysRole.getRoleKey());
        List<SysRole> list = sysRolePanacheQuery.list();
        if (!CollectionUtils.isEmpty(list)) {
            throw new ServiceException("角色名称或者角色Key已经存在！");
        }
        sysRoleRepository.persist(sysRole);
    }

    /**
     * 更新角色信息
     *
     * @param sysRole
     */
    @Transactional
    public void updateById(SysRole sysRole) {
        SysRole exits = sysRoleRepository.findById(sysRole.getRoleId());
        if (exits == null) {
            throw new ServiceException(ServiceCodeEnum.DATA_NOT_FOUND, ServiceCodeEnum.DATA_NOT_FOUND);
        }
        PanacheQuery<SysRole> sysRolePanacheQuery = sysRoleRepository.find("FROM SysRole where isDel=0 AND (roleName =?1 or roleKey=?2)", sysRole.getRoleName(), sysRole.getRoleKey());
        List<SysRole> list = sysRolePanacheQuery.list();
        if (!CollectionUtils.isEmpty(list)) {
            for (SysRole role : list) {
                if (!role.getRoleId().equals(sysRole.getRoleId())) {
                    throw new ServiceException("角色名称或者角色Key已经存在！");

                }
            }
        }

        exits.setRoleDesc(sysRole.getRoleDesc());
        exits.setMenus(sysRole.getMenus());
        exits.setRoleId(sysRole.getRoleId());
        exits.setRoleKey(sysRole.getRoleKey());
        exits.setRoleName(sysRole.getRoleName());

    }

    @Transactional
    public void deleteByIds(List<Long> deleteIds) {
        // TODO 判断是否有用户在使用该角色，如果在使用则不能删除
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ids", deleteIds);
        sysRoleRepository.update(" isDel=1 where roleId in (:ids)", paramMap);
    }

    public SysRole getById(Long roleId) {
        return sysRoleRepository.find("FROM SysRole where isDel=0 and role_id = :roleId", roleId).singleResult();
    }

    public List<SysRole> getRoleList(RoleQueryVo roleQueryVo) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM SysRole where isDel = 0");
        Map<String, Object> paramMap = new HashMap<>();
        if (!StringUtil.isNullOrEmpty(roleQueryVo.getRoleName())) {
            hql.append(" AND roleName like  :roleName");
        }
        if (roleQueryVo.getDateType() != null
                && roleQueryVo.getStartTime() != null
                && roleQueryVo.getEndTime() != null) {
            if (roleQueryVo.getDateType() == 1) {
                hql.append("AND createTime >= :startTime AND createTime <= :endTime");
            }
            if (roleQueryVo.getDateType() == 2) {
                hql.append("AND updateTime >= :startTime AND updateTime <= :endTime");
            }
            paramMap.put("startTime", roleQueryVo.getStartTime());
            paramMap.put("endTime", roleQueryVo.getEndTime());
        }
        return sysRoleRepository.find(hql.toString(), Sort.by("createTime", Sort.Direction.Descending), paramMap).page(roleQueryVo.getPageNum(), roleQueryVo.getPageSize()).list();

    }
}
