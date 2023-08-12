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
@Table(name = "error_log")
@Schema(name = "错误日志类")
public class ErrorLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_log_id",columnDefinition = " bigint NOT NULL COMMENT '错误日志Id'")
    @SchemaProperty(name = "错误日志Id")
    private Long errorLogId;
}
