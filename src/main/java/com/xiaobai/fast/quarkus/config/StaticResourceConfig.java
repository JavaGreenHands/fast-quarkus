package com.xiaobai.fast.quarkus.config;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FileSystemAccess;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class StaticResourceConfig {
    @Inject
    Vertx vertx;

    @Inject
    Router router;

    @Inject
    ApplicationConfig applicationConfig;

    void onStart(@Observes StartupEvent startupEvent){
        configure(router,vertx);
    }
    public  void configure(Router router, Vertx vertx) {
        // 配置本地路径为 /data 的静态资源处理
        router
                .route(applicationConfig.getStaticResourcePath()+"/*")
                .handler(StaticHandler.create(FileSystemAccess.ROOT,applicationConfig.getUploadPath())
                .setCachingEnabled(false) // 可选配置，禁用缓存
        );
    }
}
