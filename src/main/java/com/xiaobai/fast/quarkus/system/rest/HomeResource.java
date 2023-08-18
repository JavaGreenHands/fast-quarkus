package com.xiaobai.fast.quarkus.system.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/")
@Tag(name = "主页",description = "主页描述")
public class HomeResource {


    @GET
    @Path(("/welcome"))
    public String index(){
        return "欢迎来到Fast-Quarkus!";
    }

}
