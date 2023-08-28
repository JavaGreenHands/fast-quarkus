package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.core.interceptor.LogEvent;
import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.util.DateUtils;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import com.xiaobai.fast.quarkus.system.domain.SysUser;
import com.xiaobai.fast.quarkus.system.domain.vo.SysUserQueryVo;
import com.xiaobai.fast.quarkus.system.service.SysUserService;
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
@Path("/user")
@Tag(name = "用户管理", description = "用户管理")
public class SysUserResource {

    @Inject
    SysUserService sysUserService;

    @GET
    @Path("/list")
    @Operation(summary = "获取用户列表", description = "获取用户列表")
    @Parameters({

            @Parameter(name = "userName", description = "用户名称"),
            @Parameter(name = "userStatus", description = "用户状态1-正常 2-禁止登录"),
            @Parameter(name = "dateType", description = "查询日期类型 1-创建时间 2-更新时间"),
            @Parameter(name = "startTime", description = "查询开始时间"),
            @Parameter(name = "endTime", description = "查询结束时间"),

            @Parameter(name = "pageNum", description = "分页参数-页码", required = true),
            @Parameter(name = "pageSize", description = "分页参数-每页大小", required = true)
    })
    @APIResponse(
            responseCode = "200",
            description = "系统用户列表",
            content = @Content(
                    schema = @Schema(implementation = SysUser.class, type = SchemaType.ARRAY))
    )
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<SysUser> getUserList(
            @QueryParam("userName") String userName,
            @QueryParam("userStatus") Integer userStatus,
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
        return sysUserService.getUserList(new SysUserQueryVo(pageNum, pageSize,dateType, startDate,endDate,userName,userStatus));

    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "添加用户", description = "添加用户")
    @LogEvent("添加用户")
    public R adduser(@Valid @ConvertGroup(to = ValidationGroups.Create.class) SysUser SysUser) {
        sysUserService.addUser(SysUser);
        return R.ok();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @LogEvent("修改用户")
    @Operation(summary = "修改用户", description = "修改用户")
    public R editUser(@Valid @ConvertGroup(to = ValidationGroups.Update.class) SysUser SysUser) {
        sysUserService.updateById(SysUser);
        return R.ok();
    }

    @DELETE
    @Path("/{ids}")
    @Operation(summary = "根据id删除用户", description = "ids是根据逗号,分割的菜单id")
    @LogEvent("根据id删除用户")
    public R deleteUserByIds(@PathParam("ids") String ids) {
        List<Long> deleteIds = Arrays.stream(ids.split(",")).map(Long::valueOf).toList();
        sysUserService.deleteByIds(deleteIds);
        return R.ok();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "获取用户详情", description = "根据用户Id获取用户详情")
    @Parameters({
            @Parameter(name = "id", description = "用户Id")
    })
    @APIResponseSchema(value = SysUser.class, responseDescription = "用户详情", responseCode = "200")
    @Produces(value = MediaType.APPLICATION_JSON)
    public SysUser getById(@PathParam("id") Long userId) {
        return sysUserService.getById(userId);
    }

}
