package com.xiaobai.fast.quarkus.core.base;

import com.xiaobai.fast.quarkus.core.util.DateUtils;
import com.xiaobai.fast.quarkus.seurity.SecurityUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.resteasy.core.ResteasyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@ApplicationScoped
public class AuditEntityListener {
    private static final Logger log = LoggerFactory.getLogger(AuditEntityListener.class);
    @PrePersist
    public void PrePersist(Object entity){
        try {
            if(entity instanceof BaseEntity){
                BaseEntity baseEntity = BaseEntity.class.cast(entity);
                baseEntity.setCreateTime(DateUtils.getNowDate());
                baseEntity.setUpdateTime(DateUtils.getNowDate());
                baseEntity.setIsDel(0);
                baseEntity.setUpdateName(getCurrentUserName());
                baseEntity.setCreateName(getCurrentUserName());
            }
        }catch (Exception e){
            log.error("自动填充错误",e);
        }
        System.out.println("开始保存--"+entity.toString());
    }

    private String getCurrentUserName() {
       return SecurityUtils.getUserInfo().getUsername();

    }

    @PreUpdate
    public void PreUpdate(Object entity){
        System.out.println("开始更新--"+entity.toString());
    }

    @PostPersist
    public void PostPersist(Object entity){
        System.out.println("结束保存--"+entity.toString());
    }

    @PostUpdate
    public void PostUpdate(Object entity){
        System.out.println("结束更新--"+entity.toString());
    }
}
