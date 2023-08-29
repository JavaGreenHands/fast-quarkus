package com.xiaobai.fast.quarkus.restclient;

import com.xiaobai.fast.quarkus.system.domain.vo.HttpBinVo;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
@Dependent
public interface CustomRestClient {

    @GET
    @Path("/get")
    HttpBinVo getFromHttpBin(@QueryParam("applicationName") String applicationName,
                             @QueryParam("version") String version);


    @POST
    @Path("/post")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
	String getFromHttpBinPostForm(@FormParam("applicationName") String applicationName,
                                  @FormParam("version") String version);

    @POST
    @Path("/post")
    @Consumes(value = MediaType.APPLICATION_JSON)
    String getFromHttpBinPostJson(Map<String, Object> map);



}
