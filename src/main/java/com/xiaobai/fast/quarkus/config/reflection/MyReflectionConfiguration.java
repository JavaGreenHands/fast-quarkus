package com.xiaobai.fast.quarkus.config.reflection;

import com.xiaobai.fast.quarkus.core.response.R;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.jwt.build.impl.JwtProviderImpl;

/**
 * 注册反射
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@RegisterForReflection(targets ={ R.class, JwtProviderImpl.class})
public class MyReflectionConfiguration {
}
