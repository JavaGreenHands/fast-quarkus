package com.xiaobai.fast.quarkus.system.domain.vo;

import com.xiaobai.fast.quarkus.core.request.BaseQueryVo;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Schema(name = "角色查询Vo")
public class RoleQueryVo extends BaseQueryVo {
    @Schema(name = "角色名称")
    private String roleName;

    public RoleQueryVo(Integer pageNum, Integer pageSize) {
        super(pageNum, pageSize);
    }

    public RoleQueryVo(Integer pageNum, Integer pageSize, Integer dateType, Date createTime, Date endTime, String roleName) {
        super(pageNum, pageSize, dateType, createTime, endTime);
        this.roleName = roleName;

    }

    public RoleQueryVo() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
