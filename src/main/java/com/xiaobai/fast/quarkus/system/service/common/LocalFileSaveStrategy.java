package com.xiaobai.fast.quarkus.system.service.common;

import com.xiaobai.fast.quarkus.config.ApplicationConfig;
import com.xiaobai.fast.quarkus.config.Constants;
import com.xiaobai.fast.quarkus.system.domain.FileInfo;
import com.xiaobai.fast.quarkus.system.repository.FileInfoRepository;
import com.xiaobai.fast.quarkus.system.service.FileInfoService;
import com.xiaobai.fast.quarkus.utils.FileUtils;
import com.xiaobai.fast.quarkus.utils.IdUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 本地文件存储策略
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class LocalFileSaveStrategy implements FileUploadStrategy{
    @Inject
    ApplicationConfig applicationConfig;
    @Inject
    FileInfoService fileInfoService;
    /**
     * 上传文件保存的类型
     *
     * @return 类型名称
     * @see FileSaveType
     */
    @Override
    public String getSaveFileType() {
        return FileSaveType.LOCAL.getName();
    }

    /**
     * 保存文件
     *
     * @param inputStream 文件输入流
     * @param realFileName    文件名称
     */
    @Override
    public FileInfo saveFile(InputStream inputStream, String realFileName) {
        String suffix = FileUtils.getSuffix(realFileName);
        String fileName = IdUtils.getSimpleUUID()+ Constants.DOT+suffix;
        try (InputStream fileStream = inputStream) {
            // 指定上传的文件路径
            String uploadDir = applicationConfig.getUploadPath();
            Files.copy(fileStream, Paths.get(uploadDir, fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileRealName(realFileName);
        fileInfo.setAccessUrl(applicationConfig.getServerUrl()+applicationConfig.getStaticResourcePath()+ Constants.SLASH+fileName);
        fileInfo.setSaveFileType(FileSaveType.LOCAL.getName());
        fileInfo.setLocalFilePreFixPath(applicationConfig.getUploadPath());
        fileInfoService.saveFileInfo(fileInfo);
        return fileInfo;
    }


}
