package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.core.response.R;
import com.xiaobai.fast.quarkus.core.validator.ValidationGroups;
import com.xiaobai.fast.quarkus.system.service.SysMenuService;
import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import com.xiaobai.fast.quarkus.system.domain.vo.MenuQueryVo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/menu")
@Tag(name = "菜单管理",description = "菜单管理")
public class SysMenuResource {

    @Inject
    SysMenuService sysMenuService;

    @GET
    @Path("/list")
    @Operation(summary = "获取菜单列表")
    public Response getMenuList(MenuQueryVo queryVo){
        List<SysMenu> sysMenuList = sysMenuService.getMenuList(queryVo);
        return Response
                .ok()
                .entity(sysMenuList)
                .build();
    }
    @POST
    @Path("")
    public Response addMenu(@Valid @ConvertGroup(to = ValidationGroups.Create.class) SysMenu sysMenu){
        sysMenuService.addMenu(sysMenu);
        return Response.ok().entity("SUCCESS").build();
    }

}
