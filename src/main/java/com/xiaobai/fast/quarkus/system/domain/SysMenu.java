package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Entity
@Table(name = "sys_menu")
@Schema(name = "系统菜单类")
public class SysMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {ValidationGroups.Update.class})
    @Column(name = "menu_id")
    @SchemaProperty(name = "系统菜单Id")
    @Null(groups = ValidationGroups.Create.class)
    private Long menuId;
    @Column(name = "parent_id")
    @SchemaProperty(name = "上级菜单Id")
    @NotNull(groups = ValidationGroups.Create.class,message = "上级菜单ID不能为空")
    @NotNull(groups = ValidationGroups.Update.class,message = "上级菜单ID不能为空")
    private Long parentId;
    @Column(name = "menu_name")
    @SchemaProperty(name = "菜单标题")
    @NotEmpty(groups = ValidationGroups.Create.class,message = "菜单标题不能为空")
    @NotEmpty(groups = ValidationGroups.Update.class,message = "菜单标题不能为空")
    @Length(groups = ValidationGroups.Create.class,min = 1,max = 255,message = "菜单标题长度应该在1-255个字符")
    private String menuName;

    @Column(name = "order_num")
    @SchemaProperty(name = "排序")
    @NotNull(groups = ValidationGroups.Create.class,message = "排序值不能为空")
    @NotNull(groups = ValidationGroups.Update.class,message = "排序值不能为空")
    private Integer sortBy;

    @Column(name = "status")
    @SchemaProperty(name = "0-正常 1-禁用")
    private Integer status;

    @Column(name = "url")
    @SchemaProperty(name = "请求地址")
    @NotEmpty(groups = ValidationGroups.Create.class,message = "请求地址不能为空")
    @NotEmpty(groups = ValidationGroups.Update.class,message = "请求地址不能为空")
    private String url;

    @Column(name = "target")
    @SchemaProperty(name = "打开方式（menuItem页签 menuBlank新窗口）")
    @NotEmpty(groups = ValidationGroups.Create.class,message = "打开方式不能为空")
    @NotEmpty(groups = ValidationGroups.Update.class,message = "打开方式不能为空")
    private String target;
    @Column(name = "menu_type")
    @SchemaProperty(name = "菜单类型（1目录 2菜单 3按钮）")
    @NotNull(groups = ValidationGroups.Create.class,message = "菜单类型不能为空")
    @NotNull(groups = ValidationGroups.Update.class,message = "菜单类型不能为空")
    private Integer menuType;
    @Column(name = "visible")
    @SchemaProperty(name = "是否隐藏 0-显示 1-隐藏")
    @NotNull(groups = ValidationGroups.Create.class,message = "是否隐藏不能为空")
    @NotNull(groups = ValidationGroups.Update.class,message = "是否隐藏不能为空")
    private Integer visible;
    @Column(name = "is_refresh")
    @SchemaProperty(name = "是否刷新0-刷新 1-不刷新")
    @NotNull(groups = ValidationGroups.Create.class,message = "是否刷新不能为空")
    @NotNull(groups = ValidationGroups.Update.class,message = "是否刷新不能为空")
    private Integer isRefresh;
    @Column(name = "permission_string")
    @SchemaProperty(name = "权限标识字符串")
    private String permissionString;
    @Column(name = "icon")
    @SchemaProperty(name = "菜单图标")
    @NotEmpty(groups = ValidationGroups.Create.class,message = "菜单图标 不能为空")
    @NotEmpty(groups = ValidationGroups.Update.class,message = "菜单图标 不能为空")
    private String icon;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getSortBy() {
        return sortBy;
    }

    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(Integer isRefresh) {
        this.isRefresh = isRefresh;
    }

    public String getPermissionString() {
        return permissionString;
    }

    public void setPermissionString(String permissionString) {
        this.permissionString = permissionString;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "menuId=" + menuId +
                ", parent_id=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", sortBy=" + sortBy +
                ", url='" + url + '\'' +
                ", target='" + target + '\'' +
                ", menuType=" + menuType +
                ", visible=" + visible +
                ", isRefresh=" + isRefresh +
                ", permissionString='" + permissionString + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
