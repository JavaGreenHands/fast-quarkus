package com.xiaobai.fast.quarkus.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
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
public class BaseEntity  implements Serializable {

    @Column(name = "create_name")
    @Schema(name = "createName",description = "创建用户名称-不需要填写",hidden = true)
    @Null(groups = ValidationGroups.Create.class,message = "createName 必须为null")
    protected String createName;

    @Column(name = "update_name")
    @Schema(name = "updateName" ,description = "更新用户名称-不需要填写",hidden = true)
    @Null(groups = ValidationGroups.Create.class,message = "updateName 必须为null")
    protected String updateName;

    @Column(name = "create_time")
    @Schema(name = "createTime",description = "创建时间-不需要填写",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Null(groups = ValidationGroups.Create.class,message = "createTime 必须为null")
    protected Date createTime;

    @Column(name = "update_time")
    @Schema(name = "updateTime",description = "更新时间-不需要填写",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Null(groups = ValidationGroups.Create.class,message = "updateTime 必须为null")
    protected Date updateTime;

    @Schema(name = "isDel",description = "逻辑删除字段 0-未删除 -1删除 -不需要填写",hidden = true)
    @Column(name = "is_del")
    @Null(groups = ValidationGroups.Create.class,message = "isDel 必须为null")
    protected Integer isDel;

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
