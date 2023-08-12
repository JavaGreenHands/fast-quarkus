package com.xiaobai.fast.quarkus.core.base;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/8/12
 * @since 1.0
 */
@ApplicationScoped
public class AuditEntityListener {
    @PrePersist
    public void PrePersist(Object entity){
        System.out.println("开始保存--"+entity.toString());
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