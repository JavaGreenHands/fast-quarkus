package com.xiaobai.fast.quarkus.core;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import org.jboss.resteasy.core.ResteasyContext;

import java.io.IOException;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/20
 * @since 1.0
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {
    protected String[] rolesAllowed;
    protected boolean denyAll;
    protected boolean permitAll;

    public RequestFilter(final String[] rolesAllowed, final boolean denyAll, final boolean permitAll) {
        this.rolesAllowed = rolesAllowed;
        this.denyAll = denyAll;
        this.permitAll = permitAll;
    }
    private static final Logger LOG = Logger.getLogger(RequestFilter.class);
    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
        LOG.info(String.format("headers: %s,Method: %s,mediaType: %s,uri:%s",
                rc.getHeaders(),
                rc.getMethod(),
                rc.getMediaType(),
                rc.getUriInfo().getPath()));

        if (denyAll) {
            throw new ForbiddenException(
                    Response.status(403).entity("Access forbidden: role not allowed").type("text/html;charset=UTF-8").build());
        }
        if (permitAll)
            return;
        if (rolesAllowed != null) {
            SecurityContext context = ResteasyContext.getContextData(SecurityContext.class);
            if (context != null) {
                for (String role : rolesAllowed) {
                    if (context.isUserInRole(role))
                        return;
                }
                throw new ForbiddenException(Response.status(403).entity("Access forbidden: role not allowed")
                        .type("text/html;charset=UTF-8").build());
            }
        }
        if(rolesAllowed == null && !denyAll && !permitAll ){
            throw new ForbiddenException(Response.status(403).entity("Access forbidden: role not allowed")
                    .type("text/html;charset=UTF-8").build());
        }
        return;
    }
}
