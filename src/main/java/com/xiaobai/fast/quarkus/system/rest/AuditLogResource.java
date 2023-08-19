package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.system.domain.AuditLog;
import com.xiaobai.fast.quarkus.system.service.AuditLogService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/audit")
@Tag(name = "操作审计日志管理", description = "操作审计日志管理")
public class AuditLogResource {

    @Inject
    AuditLogService auditLogService;

    @GET
    @Path("/list")
    @Operation(summary = "获取审计日志列表")
    @Parameters({
            @Parameter(name = "logTitle", description = "日志标题"),
            @Parameter(name = "pageNum", description = "分页参数-页码",required = true),
            @Parameter(name = "pageSize", description = "分页参数-每页大小",required = true)
    })
    public List<AuditLog> getAuditLogList(
            @QueryParam(value = "logTitle") String logTitle,
            @Valid @NotNull(message = "分页参数不能为空") @QueryParam("pageNum") Integer pageNum,
            @Valid @NotNull(message = "分页参数不能为空") @QueryParam("pageSize") Integer pageSize) {
        return auditLogService.getAuditLogList(logTitle, pageNum, pageSize);
    }

}
