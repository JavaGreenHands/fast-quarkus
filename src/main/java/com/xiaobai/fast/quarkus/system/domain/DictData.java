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
@Table(name = "dict_data")
@Schema(name = "字典数据表")
public class DictData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_data_id",columnDefinition = " bigint NOT NULL COMMENT '字典数据Id'")
    @SchemaProperty(name = "字典数据Id")
    private Long dictDataId;
}
