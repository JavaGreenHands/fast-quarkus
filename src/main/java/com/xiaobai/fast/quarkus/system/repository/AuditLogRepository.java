package com.xiaobai.fast.quarkus.system.repository;

import com.xiaobai.fast.quarkus.system.domain.AuditLog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 * 操作审计日志-数据访问层
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class AuditLogRepository implements PanacheRepository<AuditLog> {

    /**
     * 保存操作审计日志
     * @param auditLog 操作审计日志
     */
    @Transactional(rollbackOn = Exception.class)
    public void saveAuditLog(AuditLog auditLog){
        this.persist(auditLog);
    }

}
