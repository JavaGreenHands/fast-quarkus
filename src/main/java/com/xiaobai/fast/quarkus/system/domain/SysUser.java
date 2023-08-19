package com.xiaobai.fast.quarkus.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;


import java.util.Date;
import java.util.Set;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Entity
@Table(name = "sys_user")
@Schema(name = "系统用户类")
public class SysUser extends BaseEntity {

    @Id
    @NotNull(groups = {ValidationGroups.Update.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @SchemaProperty(name = "系统用户Id")
    private Long userId;

    @Column(name = "username")
    @SchemaProperty(name = "用户名称")
    private String username;
    @Column(name = "nick_name")
    @SchemaProperty(name = "用户昵称")
    private String nickName;
    @Column(name = "phone_number")
    @SchemaProperty(name = "手机号")
    private String phoneNumber;
    @Column(name = "avatar")
    @SchemaProperty(name = "用户头像")
    private String avatar;
    @Column(name = "user_pwd")
    @SchemaProperty(name = "用户密码")
    private String userPwd;

    @Column(name = "user_status")
    @SchemaProperty(name = "用户状态 1-正常 2-禁止登录")
    private Integer userStatus;

    @Column(name = "last_modify_password_date")
    @SchemaProperty(name = "最后一次修改密码时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyPasswordDate;

    @ManyToMany
    @JoinColumn(name = "user_role")
    private Set<SysRole> sysRoleSet;

    public Set<SysRole> getSysRoleSet() {
        return sysRoleSet;
    }

    public void setSysRoleSet(Set<SysRole> sysRoleSet) {
        this.sysRoleSet = sysRoleSet;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getLastModifyPasswordDate() {
        return lastModifyPasswordDate;
    }

    public void setLastModifyPasswordDate(Date lastModifyPasswordDate) {
        this.lastModifyPasswordDate = lastModifyPasswordDate;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", lastModifyPasswordDate=" + lastModifyPasswordDate +
                '}';
    }
}
