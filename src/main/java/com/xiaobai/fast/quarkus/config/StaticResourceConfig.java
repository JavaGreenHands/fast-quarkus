package com.xiaobai.fast.quarkus.config;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FileSystemAccess;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class StaticResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourceConfig.class);
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
        logger.info("静态资源配置加载:访问路径{},本地资源路径:{}",applicationConfig.getStaticResourcePath()+"/*",applicationConfig.getUploadPath());
    }
}
