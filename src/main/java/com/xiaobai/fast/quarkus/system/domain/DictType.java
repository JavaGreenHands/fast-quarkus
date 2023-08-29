package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.runtime.CacheKeyParameterPositions;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import java.util.List;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@Entity
@Table(name = "fq_dict_type")
@Schema(name = "字典类型表")
@CacheKeyParameterPositions(0)
public class DictType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_type_id")
    @Schema(name = "dictTypeId",description = "字典类型Id")
    @Null(message = "新增时dictTypeId为空",groups = ValidationGroups.Create.class)
    @NotNull(message = "修改时dictTypeId不为空",groups = ValidationGroups.Update.class)
    private Long dictTypeId;

    @Schema(name = "dictTypeName",description = "字典类型名称")
    @Column(name = "dict_type_name")
    @NotEmpty(message = "dictTypeName不为空",groups = ValidationGroups.Create.class)
    @NotEmpty(message = "dictTypeName不为空",groups = ValidationGroups.Update.class)
    private String dictTypeName;

    @Column(name = "dict_type")
    @Schema(name = "dictType",description = "字典类型")
    @NotEmpty(message = "dictType不为空",groups = ValidationGroups.Create.class)
    @NotEmpty(message = "dictType不为空",groups = ValidationGroups.Update.class)
    private String dictType;

    @Column(name = "status")
    @Schema(name = "status",description = "字典状态 1-正常 2-停用")
    @NotEmpty(message = "status不为空",groups = ValidationGroups.Create.class)
    @NotEmpty(message = "status不为空",groups = ValidationGroups.Update.class)
    private String status;

    @Column(name = "remake")
    @Schema(name = "remake",description = "备注")
    @NotEmpty(message = "remake不为空",groups = ValidationGroups.Create.class)
    @NotEmpty(message = "remake不为空",groups = ValidationGroups.Update.class)
    private String remake;

    @OneToMany
    @JoinColumn(name = "dict_type_id")
    private List<DictData> dictDataList;

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public List<DictData> getDictDataList() {
        return dictDataList;
    }

    public void setDictDataList(List<DictData> dictDataList) {
        this.dictDataList = dictDataList;
    }
}
