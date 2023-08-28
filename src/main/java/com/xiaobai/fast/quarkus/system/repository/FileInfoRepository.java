package com.xiaobai.fast.quarkus.system.repository;

import com.xiaobai.fast.quarkus.system.domain.FileInfo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * 文件保存-数据访问层
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class FileInfoRepository implements PanacheRepository<FileInfo> {
}
