package com.xiaobai.fast.quarkus.core.filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {
    private static final Logger log = LoggerFactory.getLogger(ResponseFilter.class);

    /**
     * Filter method called after a response has been provided for a request (either by a {@link ContainerRequestFilter
     * request filter} or by a matched resource method.
     * <p>
     * Filters in the filter chain are ordered according to their {@code jakarta.annotation.Priority} class-level annotation
     * value.
     * </p>
     *
     * @param requestContext  request context.
     * @param responseContext response context.
     * @throws IOException if an I/O exception occurs.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        log.info("""
                 \n request:[
                         headers: {};
                         Method: {};
                         mediaType: {};
                         uri:{};
                        ]
                        response:[
                        allowedMethods: {};
                        language: {};
                        headers: {};
                        mediaType: {};
                        status: {};
                        ]
                        """,
                requestContext.getHeaders(),
                requestContext.getMethod(),
                requestContext.getMediaType(),
                requestContext.getUriInfo().getPath(),
                responseContext.getAllowedMethods(),
                responseContext.getLanguage(),
                responseContext.getHeaders(),
                responseContext.getMediaType(),
                responseContext.getStatus());

    }
}
