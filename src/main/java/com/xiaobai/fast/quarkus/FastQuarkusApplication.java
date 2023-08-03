package com.xiaobai.fast.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/20
 * @since 1.0
 */
@QuarkusMain
public class FastQuarkusApplication {
    public static void main(String[] args) {
        Quarkus.run(args);
        System.out.println("xxxxxx");
    }
}
