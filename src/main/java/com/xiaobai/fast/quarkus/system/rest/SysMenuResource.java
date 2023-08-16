package com.xiaobai.fast.quarkus.system.rest;

import com.oracle.svm.core.annotate.Delete;
import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import com.xiaobai.fast.quarkus.system.domain.vo.MenuQueryVo;
import com.xiaobai.fast.quarkus.system.service.SysMenuService;
import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.links.Link;
import org.eclipse.microprofile.openapi.annotations.links.LinkParameter;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            @Parameter(name = "status", description = "菜单状态 0-正常 1-禁用"),
            @Parameter(name = "parentId", description = "上级菜单Id"),
            @Parameter(name = "pageNum", description = "分页参数-页码"),
            @Parameter(name = "pageSize", description = "分页参数-每页大小"),
    })
    @APIResponse(
            responseCode = "200",
            description = "系统菜单列表",
            content = @Content(
                    schema = @Schema(implementation = SysMenu.class,type = SchemaType.ARRAY))
    )
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getMenuList(
            @QueryParam("menuName") String menuName,
            @QueryParam("status") Integer status,
            @QueryParam("parentId") Long parentId,
            @QueryParam("pageNum") Integer pageNum,
            @QueryParam("pageSize") Integer pageSize) {

        List<SysMenu> sysMenuList = sysMenuService.getMenuList(new MenuQueryVo(pageNum, pageSize, menuName, status, parentId));
        return Response
                .ok()
                .entity(sysMenuList)
                .build();
    }

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMenu(@Valid @ConvertGroup(to = ValidationGroups.Create.class) SysMenu sysMenu) {
        sysMenuService.addMenu(sysMenu);
        return Response.ok().entity(R.successBody()).build();
    }
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMenu(@Valid @ConvertGroup(to = ValidationGroups.Update.class) SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Response.ok().entity(R.successBody()).build();
    }
    @DELETE
    @Path("/{ids}")
    public Response deleteMenuByIds(@PathParam("ids") String ids) {
        List<Long> deleteIds = Arrays.stream(ids.split(",")).map(Long::valueOf).toList();

        sysMenuService.deleteByIds(deleteIds);

        return Response.ok().entity(R.successBody()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "获取菜单详情", description = "根据菜单Id获取菜单详情")
    @Parameters({
            @Parameter(name = "id", description = "菜单Id")
    })
    @APIResponseSchema(value = SysMenu.class, responseDescription = "菜单详情", responseCode = "200")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long menuId) {
        SysMenu sysMenu = sysMenuService.getById(menuId);
        return Response.ok().entity(sysMenu).build();
    }

}
