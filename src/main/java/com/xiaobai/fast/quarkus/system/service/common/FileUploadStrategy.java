package com.xiaobai.fast.quarkus.system.service.common;

import com.xiaobai.fast.quarkus.system.domain.FileInfo;

import java.io.InputStream;

/**
 * 文件上传策略
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public interface FileUploadStrategy {

    /**
     * 上传文件保存的类型
     * @return 类型名称
     * @see FileSaveType
     */
    String getSaveFileType();

    /**
     * 保存文件
     * @param inputStream 文件输入流
     * @param fileName 文件名称
     */
    FileInfo saveFile(InputStream inputStream, String fileName);
}
