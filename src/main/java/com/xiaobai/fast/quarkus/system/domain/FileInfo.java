package com.xiaobai.fast.quarkus.system.domain;

import com.xiaobai.fast.quarkus.core.base.BaseEntity;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * 文件管理工具类
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Entity
@Table(name = "fq_file_info")
@Schema(name = "文件信息")
public class FileInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    @Schema(name = "file_id",description = "文件Id")
    private Long fileId;

    @Column(name = "file_real_name")
    @Schema(name = "fileRealName",description ="原始文件名称" )
    private String fileRealName;

    @Column(name = "access_url")
    @Schema(name = "accessUrl",description = "访问地址")
    private String accessUrl;

    @Column(name = "save_file_type")
    @Schema(name = "saveFileType",description = "保存文件方式")
    private String saveFileType;

    @Schema(name = "localFilePreFixPath",description = "服务器文件路径前缀，只有local的方式存在")
    private String localFilePreFixPath;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getSaveFileType() {
        return saveFileType;
    }

    public void setSaveFileType(String saveFileType) {
        this.saveFileType = saveFileType;
    }

    public String getLocalFilePreFixPath() {
        return localFilePreFixPath;
    }

    public void setLocalFilePreFixPath(String localFilePreFixPath) {
        this.localFilePreFixPath = localFilePreFixPath;
    }
}
