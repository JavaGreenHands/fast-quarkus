package com.xiaobai.fast.quarkus.system.domain.vo;

import com.xiaobai.fast.quarkus.core.request.BaseQueryVo;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Schema(name = "菜单查询VO")
public class MenuQueryVo extends BaseQueryVo {
    @SchemaProperty(name = "菜单标题")
    private String menuName;

    @SchemaProperty(name = "上级菜单Id")
    private Long parentId;

    public MenuQueryVo(Integer pageNum, Integer pageSize, String menuName, Long parentId) {
        super(pageNum, pageSize);
        this.menuName = menuName;
        this.parentId = parentId;
    }

    public MenuQueryVo(Integer pageNum, Integer pageSize,  String menuName, Long parentId,Integer dateType, Date createTime, Date endTime) {
        super(pageNum, pageSize, dateType, createTime, endTime);
        this.menuName = menuName;
        this.parentId = parentId;
    }

    public MenuQueryVo() {
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
