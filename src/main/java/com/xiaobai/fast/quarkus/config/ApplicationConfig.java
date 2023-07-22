package com.xiaobai.fast.quarkus.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/18
 * @since 1.0
 */
@ApplicationScoped
public class ApplicationConfig {

    @ConfigProperty(name = "upload.path")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
