package com.xiaobai.fast.quarkus.system.domain.vo;

import com.xiaobai.fast.quarkus.core.request.BaseQueryVo;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * 字典数据查询VO
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Schema(name = "DictDataQueryVo" ,description = "字典数据查询VO")
public class DictDataQueryVo extends BaseQueryVo {

    @Schema(name = "字典类型Id")
    @QueryParam("dictTypeId")
    private Long dictTypeId;
    @Schema(name = "labelName",description = "字典名称")
    @QueryParam("labelName")
    private String labelName;

    @Schema(name = "status",description = "状态 1-正常 2-停用")
    @QueryParam("status")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
