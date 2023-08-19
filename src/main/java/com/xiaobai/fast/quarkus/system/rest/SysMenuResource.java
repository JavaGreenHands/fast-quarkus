package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.core.util.DateUtils;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import com.xiaobai.fast.quarkus.core.interceptor.LogEvent;
import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import com.xiaobai.fast.quarkus.system.domain.vo.MenuQueryVo;
import com.xiaobai.fast.quarkus.system.service.SysMenuService;
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
@Path("/menu")
@Tag(name = "菜单管理", description = "菜单管理")
public class SysMenuResource {

    @Inject
    SysMenuService sysMenuService;

    @GET
    @Path("/list")
    @Operation(summary = "获取菜单列表", description = "获取菜单列表")
    @Parameters({
            @Parameter(name = "menuName", description = "菜单标题"),
            @Parameter(name = "parentId", description = "上级菜单Id"),
            @Parameter(name = "dateType", description = "查询日期类型 1-创建时间 2-更新时间"),
            @Parameter(name = "startTime", description = "查询开始时间"),
            @Parameter(name = "endTime", description = "查询结束时间"),
            @Parameter(name = "pageNum", description = "分页参数-页码",required = true),
            @Parameter(name = "pageSize", description = "分页参数-每页大小",required = true),
    })
    @APIResponse(
            responseCode = "200",
            description = "系统菜单列表",
            content = @Content(
                    schema = @Schema(implementation = SysMenu.class, type = SchemaType.ARRAY))
    )
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<SysMenu> getMenuList(
            @QueryParam("menuName") String menuName,
            @QueryParam("parentId") Long parentId,
            @QueryParam("dateType") Integer dateType,
            @QueryParam("startTime") String startTime,
            @QueryParam("endTime") String endTime,
            @Valid @NotNull(message = "分页参数不能为空") @QueryParam("pageNum") Integer pageNum,
            @Valid @NotNull(message = "分页参数不能为空")   @QueryParam("pageSize") Integer pageSize) {

        Date startDate =null;
        Date endDate =null;

        if(!StringUtil.isNullOrEmpty(startTime) && !StringUtil.isNullOrEmpty(endTime)){
            startDate =  DateUtils.parseDateTime(startTime);
            endDate =  DateUtils.parseDateTime(endTime);

        }

        return sysMenuService.getMenuList(new MenuQueryVo(pageNum, pageSize, menuName, parentId,dateType,startDate,endDate));

    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "添加菜单", description = "添加菜单")
    @LogEvent("添加菜单")
    public void addMenu(@Valid @ConvertGroup(to = ValidationGroups.Create.class) SysMenu sysMenu) {
        sysMenuService.addMenu(sysMenu);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @LogEvent("修改菜单")
    public void editMenu(@Valid @ConvertGroup(to = ValidationGroups.Update.class) SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
    }

    @DELETE
    @Path("/{ids}")
    @Operation(summary = "根据id删除菜单", description = "ids是根据逗号,分割的菜单id")
    @LogEvent("根据id删除菜单")
    public void deleteMenuByIds(@PathParam("ids") String ids) {
        List<Long> deleteIds = Arrays.stream(ids.split(",")).map(Long::valueOf).toList();

        sysMenuService.deleteByIds(deleteIds);

    }

    @GET
    @Path("/{id}")
    @Operation(summary = "获取菜单详情", description = "根据菜单Id获取菜单详情")
    @Parameters({
            @Parameter(name = "id", description = "菜单Id")
    })
    @APIResponseSchema(value = SysMenu.class, responseDescription = "菜单详情", responseCode = "200")
    @Produces(value = MediaType.APPLICATION_JSON)
    public SysMenu getById(@PathParam("id") Long menuId) {
        return sysMenuService.getById(menuId);
    }

}
