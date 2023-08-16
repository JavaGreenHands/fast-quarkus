package com.xiaobai.fast.quarkus.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
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
    @SchemaProperty(name = "创建用户名称-不需要填写",hidden = true)
    protected String createName;

    @Column(name = "update_name")
    @SchemaProperty(name = "更新用户名称-不需要填写" ,hidden = true)
    protected String updateName;

    @Column(name = "create_time")
    @SchemaProperty(name = "创建时间-不需要填写",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createTime;

    @Column(name = "update_time")
    @SchemaProperty(name = "更新时间-不需要填写",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date updateTime;

    @SchemaProperty(name = "逻辑删除字段 0-未删除 -1删除 -不需要填写",hidden = true)
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
