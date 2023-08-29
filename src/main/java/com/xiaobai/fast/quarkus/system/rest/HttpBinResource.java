package com.xiaobai.fast.quarkus.system.rest;

import com.xiaobai.fast.quarkus.restclient.CustomRestClient;
import com.xiaobai.fast.quarkus.restclient.DefaultFallbackHandler;
import com.xiaobai.fast.quarkus.system.domain.vo.HttpBinVo;
import io.smallrye.faulttolerance.DefaultFallbackHandlerProvider;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * restClient demo
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@Path("/http/bin")
@Tag(name = "HttpBinResource",description = "RestClient调用HttpBin")
public class HttpBinResource {
    @Inject
    @RestClient
    CustomRestClient client;

    @GET
    @Path("/get")
    @Retry(maxRetries = 3)
    public HttpBinVo getFromHttpBin(@QueryParam("applicationName") String applicationName,
                                 @QueryParam("version") String version){
       return client.getFromHttpBin(applicationName, version);
    }
    @GET
    @Path("/post/form")
    @Timeout(1000)
    public String getFromHttpBinPostForm(@QueryParam("applicationName") String applicationName,
                                    @QueryParam("version") String version){
        return client.getFromHttpBinPostForm(applicationName, version);
    }

    @GET
    @Path("/post/json")
    @Timeout(1)
    @Fallback(value = DefaultFallbackHandler.class)
    public String getFromHttpBinPostJson(@QueryParam("applicationName") String applicationName,
                                         @QueryParam("version") String version){
        Map<String,Object> map = new HashMap<>();
        map.put("version",applicationName);
        map.put("applicationName",applicationName);
        return client.getFromHttpBinPostJson(map);
    }
}
