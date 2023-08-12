package com.xiaobai.fast.quarkus.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import java.util.Set;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@Entity
@Table(name = "sys_menu")
@Schema(name = "系统菜单类")
public class SysMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Update.class})
    @Column(name = "menu_id",columnDefinition = "BIGINT NOT NULL COMMENT '系统菜单Id'")
    @SchemaProperty(name = "系统菜单Id")
    private Long menuId;
    @Column(name = "parent_id",columnDefinition = "BIGINT NOT NULL DEFAULT 0 COMMENT '上级菜单Id' ")
    @SchemaProperty(name = "上级菜单Id")
    private Long parent_id;
    @Column(name = "menu_name",columnDefinition = "VARCHAR(100) NOT NULL  COMMENT '菜单标题' ")
    @SchemaProperty(name = "菜单标题")
    private String menuName;

    @Column(name = "order_num" ,columnDefinition = "INT DEFAULT 999 NOT NULL COMMENT '排序'")
    @SchemaProperty(name = "排序")
    private Integer sortBy ;
    @Column(name = "url", columnDefinition = "VARCHAR(255) DEFAULT '#' COMMENT '请求地址'")
    @SchemaProperty(name = "请求地址")
    private String url;

    @Column(name = "target",columnDefinition = "VARCHAR(50) NOT NULL COMMENT '打开方式（menuItem页签 menuBlank新窗口）'")
    @SchemaProperty(name = "打开方式（menuItem页签 menuBlank新窗口）")
    private String target;
    @Column(name = "menu_type",columnDefinition = "INT  NOT NULL  COMMENT '菜单类型（1目录 2菜单 3按钮）'")
    @SchemaProperty(name = "菜单类型（1目录 2菜单 3按钮）")
    private Integer menuType;
    @Column(name = "visible",columnDefinition = "INT  DEFAULT 0 COMMENT '是否隐藏 0-显示 1-隐藏'")
    @SchemaProperty(name = "是否隐藏 0-显示 1-隐藏")
    private Integer visible;
    @Column(name = "is_refresh",columnDefinition = "INT DEFAULT 1 COMMENT '是否刷新0-刷新 1-不刷新'" )
    @SchemaProperty(name = "是否刷新0-刷新 1-不刷新")
    private Integer isRefresh;
    @Column(name = "permission_string",columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '权限标识字符串'")
    @SchemaProperty(name = "权限标识字符串")
    private String permissionString;
    @Column(name = "icon",columnDefinition = "VARCHAR(255) DEFAULT '' COMMENT '菜单图标'" )
    @SchemaProperty(name = "菜单图标")
    private String icon;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
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
                ", parent_id=" + parent_id +
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
