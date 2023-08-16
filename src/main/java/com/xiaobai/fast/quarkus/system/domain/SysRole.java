package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@Entity
@Table(name = "sys_role")
@Schema(name = "系统角色类")
public class SysRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @SchemaProperty(name = "系统角色Id")
    private Long roleId;

    @Column(name = "role_name")
    @SchemaProperty(name = "角色名称")
    private String roleName;

    @Column(name = "role_key")
    @SchemaProperty(name = "角色名称key")
    private String roleNameKey;

    @Column(name = "role_status")
    @SchemaProperty(name = "角色状态 0- 启用 1-停用")
    private Integer roleStatus;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleNameKey() {
        return roleNameKey;
    }

    public void setRoleNameKey(String roleNameKey) {
        this.roleNameKey = roleNameKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleNameKey='" + roleNameKey + '\'' +
                ", roleStatus=" + roleStatus +
                '}';
    }
}
