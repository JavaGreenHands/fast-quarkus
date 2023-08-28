package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.system.domain.FileInfo;
import com.xiaobai.fast.quarkus.system.repository.FileInfoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * 文件保存业务类型
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class FileInfoService {
    @Inject
    FileInfoRepository fileInfoRepository;
    @Transactional(rollbackOn = Exception.class)
    public void saveFileInfo(FileInfo fileInfo) {
        fileInfoRepository.persist(fileInfo);
    }
}
