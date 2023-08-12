package com.xiaobai.fast.quarkus.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */

@MappedSuperclass
@Schema(name = "数据库基类")
@EntityListeners(AuditEntityListener.class)
public class BaseEntity extends PanacheEntityBase implements Serializable {

    @Column(name = "create_name",columnDefinition = "VARCHAR(100) DEFAULT '' COMMENT '创建用户名称'")
    @Schema(name = "创建用户名称-不需要填写")
    private String createName;

    @Column(name = "update_name",columnDefinition = "VARCHAR(100) DEFAULT '' COMMENT '更新用户名称'")
    @Schema(name = "更新用户名称-不需要填写" )
    private String updateName;

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'",updatable = false)
    @Schema(name = "创建时间-不需要填写")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time",columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    @Schema(name = "更新时间-不需要填写")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "is_del",columnDefinition ="INT NOT NULL DEFAULT 0 COMMENT '逻辑删除字段 0-未删除 -1删除' " )
    @Schema(name = "逻辑删除字段 0-未删除 -1删除 -不需要填写")
    private Integer isDel;

    /* 创建对象校验分组 */
    public @interface Create {}

    /* 更新对象校验分组 */
    public @interface Update {}

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createName='" + createName + '\'' +
                ", updateName='" + updateName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDel=" + isDel +
                '}';
    }
}
