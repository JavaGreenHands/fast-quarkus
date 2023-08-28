package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.core.interceptor.LogEvent;
import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.util.DateUtils;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import com.xiaobai.fast.quarkus.system.domain.SysRole;
import com.xiaobai.fast.quarkus.system.domain.vo.RoleQueryVo;
import com.xiaobai.fast.quarkus.system.service.SysRoleService;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/role")
@Tag(name = "角色管理", description = "角色管理")
public class SysRoleResource {

    @Inject
    SysRoleService sysRoleService;

    @GET
    @Path("/list")
    @Operation(summary = "获取角色列表", description = "获取角色列表")
    @Parameters({

            @Parameter(name = "roleName", description = "角色名称"),
            @Parameter(name = "dateType", description = "查询日期类型 1-创建时间 2-更新时间"),
            @Parameter(name = "startTime", description = "查询开始时间"),
            @Parameter(name = "endTime", description = "查询结束时间"),

            @Parameter(name = "pageNum", description = "分页参数-页码", required = true),
            @Parameter(name = "pageSize", description = "分页参数-每页大小", required = true)
    })
    @APIResponse(
            responseCode = "200",
            description = "系统角色列表",
            content = @Content(
                    schema = @Schema(implementation = SysRole.class, type = SchemaType.ARRAY))
    )
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<SysRole> getRoleList(
            @QueryParam("roleName") String roleName,
            @QueryParam("dateType") Integer dateType,
            @QueryParam("startTime") String startTime,
            @QueryParam("endTime") String endTime,
            @Valid @NotNull(message = "分页参数不能为空") @QueryParam("pageNum") Integer pageNum,
            @Valid @NotNull(message = "分页参数不能为空") @QueryParam("pageSize") Integer pageSize
    ) {
        Date startDate =null;
        Date endDate =null;

        if(!StringUtil.isNullOrEmpty(startTime) && !StringUtil.isNullOrEmpty(endTime)){
            startDate =  DateUtils.parseDateTime(startTime);
            endDate =  DateUtils.parseDateTime(endTime);

        }

        return sysRoleService.getRoleList(new RoleQueryVo(pageNum, pageSize,dateType, startDate,endDate,roleName));

    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "添加角色", description = "添加角色")
    @LogEvent("添加角色")
    public R addRole(@Valid @ConvertGroup(to = ValidationGroups.Create.class) SysRole sysRole) {
        sysRoleService.addRole(sysRole);
        return R.ok();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @LogEvent("修改角色")
    @Operation(summary = "修改角色", description = "修改角色")
    public R editRole(@Valid @ConvertGroup(to = ValidationGroups.Update.class) SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return R.ok();
    }

    @DELETE
    @Path("/{ids}")
    @Operation(summary = "根据id删除角色", description = "ids是根据逗号,分割的菜单id")
    @LogEvent("根据id删除角色")
    public R deleteRoleByIds(@PathParam("ids") String ids) {
        List<Long> deleteIds = Arrays.stream(ids.split(",")).map(Long::valueOf).toList();
        sysRoleService.deleteByIds(deleteIds);
        return R.ok();

    }

    @GET
    @Path("/{id}")
    @Operation(summary = "获取角色详情", description = "根据菜单Id获取角色详情")
    @Parameters({
            @Parameter(name = "id", description = "角色Id")
    })
    @APIResponseSchema(value = SysRole.class, responseDescription = "角色详情", responseCode = "200")
    @Produces(value = MediaType.APPLICATION_JSON)
    public SysRole getById(@PathParam("id") Long roleId) {
        return sysRoleService.getById(roleId);
    }

}
