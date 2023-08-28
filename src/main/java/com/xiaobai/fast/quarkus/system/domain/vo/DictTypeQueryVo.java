package com.xiaobai.fast.quarkus.system.domain.vo;

import com.xiaobai.fast.quarkus.core.request.BaseQueryVo;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * 字典类型查询VO
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Schema(name = "DictTypeQueryVo",description = "字典类型查询VO")
public class DictTypeQueryVo extends BaseQueryVo {
    @Schema(name = "dictTypeName",description = "字典类型名称")
    @QueryParam("dictTypeName")
    private String dictTypeName;

    @Schema(name = "dictType",description = "字典类型")
    @QueryParam("dictType")
    private String dictType;

    @Schema(name = "status",description = "字典状态 1-正常 2-停用")
    @QueryParam("status")
    private String status;

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
}
