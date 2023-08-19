package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.system.domain.AuditLog;
import com.xiaobai.fast.quarkus.system.repository.AuditLogRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class AuditLogService {
    @Inject
    AuditLogRepository auditLogRepository;


    /**
     * 获取审计日志列表
     *
     * @param logTitle 日志标题
     * @param pageNum  分页参数-页码
     * @param pageSize 分页参数 -每页大小
     * @return 审计日志列表
     */
    public List<AuditLog> getAuditLogList(String logTitle, Integer pageNum, Integer pageSize) {
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM AuditLog where isDel = 0");
        Map<String, Object> paramterMap = new HashMap<>();
        if (!StringUtil.isNullOrEmpty(logTitle)) {
            hql.append(" AND  logTitle=:logTitle");
            paramterMap.put(logTitle, logTitle);
        }
        PanacheQuery<AuditLog> panacheQuery = auditLogRepository.find(hql.toString(), paramterMap);
        return panacheQuery.page(pageNum, pageSize).list();
    }
}
