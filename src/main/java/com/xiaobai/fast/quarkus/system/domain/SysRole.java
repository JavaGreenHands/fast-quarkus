package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Set;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@Entity
@Table(name = "fq_sys_role")
@Schema(name = "系统角色类")
public class SysRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @Schema(description = "系统角色Id")
    private Long roleId;

    @Column(name = "role_name")
    @Schema(description = "角色名称")
    private String roleName;

    @Column(name = "role_desc")
    @Schema(description = "角色描述")
    private String roleDesc;

    @Column(name = "role_key")
    @Schema(description = "角色名称key")
    private String roleKey;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fq_sys_roles_menus",
    joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")},
    inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "menu_id")})
    @Schema(name = "menus",description = "角色包含的菜单",hidden = true)
    private Set<SysMenu> menus;

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Set<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(Set<SysMenu> menus) {
        this.menus = menus;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleNameKey='" + roleKey + '\'' +
                '}';
    }
}
