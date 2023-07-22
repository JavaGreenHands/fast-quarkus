package com.xiaobai.fast.quarkus;

import io.quarkus.runtime.Quarkus;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/20
 * @since 1.0
 */
@ApplicationScoped
public class FastQuarkusApplication {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
