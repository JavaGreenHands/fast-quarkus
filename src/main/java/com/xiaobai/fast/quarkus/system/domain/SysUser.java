package com.xiaobai.fast.quarkus.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;


import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Entity
@Table(name = "sys_user")
@Schema(name = "系统用户类")
public class SysUser extends BaseEntity {

    @Id
    @NotNull(groups = {Update.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '系统用户Id'")
    @SchemaProperty(name = "系统用户Id")
    private Long userId;

    @Column(name = "username",columnDefinition = "VARCHAR(100) NOT NULL COMMENT '用户名称'")
    @SchemaProperty(name = "用户名称")
    private String username;
    @Column(name = "nick_name",columnDefinition = "VARCHAR(100) NOT NULL COMMENT '用户昵称'")
    @SchemaProperty(name = "用户昵称")
    private String nickName;
    @Column(name = "phone_number",columnDefinition = "VARCHAR(20) NOT NULL COMMENT '手机号'")
    @SchemaProperty(name = "手机号")
    private String phoneNumber;
    @Column(name = "avatar",columnDefinition = "VARCHAR(200) NOT NULL COMMENT '用户头像'")
    @SchemaProperty(name = "用户头像")
    private String avatar;
    @Column(name = "user_pwd",columnDefinition = "VARCHAR(100) NOT NULL COMMENT '用户密码'")
    @SchemaProperty(name = "用户密码")
    private String userPwd;

    @Column(name = "last_modify_password_date",columnDefinition = "DATETIME NOT NULL COMMENT '最后一次修改密码时间'")
    @SchemaProperty(name = "最后一次修改密码时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifyPasswordDate;

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
