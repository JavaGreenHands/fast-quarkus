package com.xiaobai.fast.quarkus.config;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class ApplicationLifecycleBean {
    @ConfigProperty(name = "quarkus.application.name")
    String applicationName;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLifecycleBean.class);

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("The {} is starting...", Optional.ofNullable(applicationName).orElse("application"));
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The {} is stopping...",Optional.ofNullable(applicationName).orElse("application"));
    }

}
