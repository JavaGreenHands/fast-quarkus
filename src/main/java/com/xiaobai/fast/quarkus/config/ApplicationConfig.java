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
    String uploadPath;

    @ConfigProperty(name = "upload.type")
    String uploadType;

    @ConfigProperty(name = "server.url")
    String serverUrl;

    @ConfigProperty(name = "static.path")
    String staticResourcePath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getStaticResourcePath() {
        return staticResourcePath;
    }

    public void setStaticResourcePath(String staticResourcePath) {
        this.staticResourcePath = staticResourcePath;
    }
}
