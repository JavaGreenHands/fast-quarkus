package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@Entity
@Table(name = "fq_dict_data")
@Schema(name = "字典数据表")
public class DictData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_data_id")
    @Schema(name = "dictDataId",description = "字典数据Id")
    @NotNull(message = "更新时dictDataId不能为空",groups = ValidationGroups.Update.class)
    @Null(message = "新增时dictDataId为空",groups = ValidationGroups.Create.class)
    private Long dictDataId;

    @Column(name = "dict_type_id")
    @Schema(name = "dictTypeId",description = "字典类型Id")
    @NotNull(message = "新增时dictDataId为空",groups = ValidationGroups.Create.class)
    private Long dictTypeId;
    @Column(name = "label_name")
    @Schema(name = "labelName",description = "字典名称")
    @NotEmpty(message = "新增时labelName为空",groups = ValidationGroups.Create.class)
    private String labelName;

    @Column(name = "label_value")
    @Schema(name = "labelValue",description = "字典值")
    @NotEmpty(message = "labelValue为空",groups = ValidationGroups.Create.class)
    private String labelValue;

    @Column(name = "sort_by")
    @Schema(name = "sortBy",description = "排序值")
    @Null(message = "sortBy不能为空",groups = ValidationGroups.Create.class)
    private Integer sortBy;

    @Column(name = "status")
    @Schema(name = "status",description = "状态 1-正常 2-停用")
    @NotEmpty(message = "status不能为空",groups = ValidationGroups.Create.class)
    private String status;

    public Long getDictDataId() {
        return dictDataId;
    }

    public void setDictDataId(Long dictDataId) {
        this.dictDataId = dictDataId;
    }

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public Integer getSortBy() {
        return sortBy;
    }

    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
