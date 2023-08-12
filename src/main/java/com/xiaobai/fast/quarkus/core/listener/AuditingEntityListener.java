package com.xiaobai.fast.quarkus.core.listener;

import jakarta.persistence.*;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class AuditingEntityListener  {
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
