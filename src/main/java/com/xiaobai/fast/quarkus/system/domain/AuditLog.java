package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@Entity
@Table(name = "audit_log")
@Schema(name = "审计日志类")
public class AuditLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_log_id")
    @Schema(name = "auditLogId",description = "审计日志Id")
    private Long auditLogId;

    @Column(name = "request_type")
    @Schema(name = "request_type",description = "请求方式")
    private String requestType;

    @Column(name = "url")
    @Schema(name = "url",description = "请求路径")
    private String url;
    @Column(name = "request_param",columnDefinition = " TEXT")
    @Schema(name = "requestParam",description = "请求参数")
    private String requestParam;

    @Column(name = "log_title")
    @Schema(name = "logTitle",description = "日志标题")
    private String logTitle;

    @Column(name = "cost_time")
    @Schema(name = "costTime",description = "耗时，单位毫秒")
    private Long costTime;
    @Column(name = "failed")
    @Schema(name = "failed",description = "是否成功 0-成功1-失败")
    private Integer failed;


    @Column(name = "err_msg",length = 2000)
    @Schema(name = "",description = "错误信息")
    private String errorMsg;

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Integer getFailed() {
        return failed;
    }

    public void setFailed(Integer failed) {
        this.failed = failed;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
