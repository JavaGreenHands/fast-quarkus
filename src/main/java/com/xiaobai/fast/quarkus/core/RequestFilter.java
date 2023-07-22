package com.xiaobai.fast.quarkus.core;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.io.IOException;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/20
 * @since 1.0
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(RequestFilter.class);
    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
        LOG.info(String.format("headers: %s,Method: %s,mediaType: %s,uri:%s",
                rc.getHeaders(),
                rc.getMethod(),
                rc.getMediaType(),
                rc.getUriInfo().getPath()));
    }
}
