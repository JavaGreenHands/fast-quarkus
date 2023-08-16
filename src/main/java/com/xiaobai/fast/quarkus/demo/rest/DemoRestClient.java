//package com.xiaobai.fast.quarkus.demo.rest;
//
//import jakarta.enterprise.context.Dependent;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
//
//import java.util.List;
//
///**
// * @author baijie <a href="mrwhite777@163.com"></a>
// * @date 2023/7/18
// * @since 1.0
// */
//@Path("/numeral")
//@RegisterRestClient
//@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
//@Dependent
//public interface DemoRestClient {
//
//    @POST
//    @Path("/ai/voice/list")
//    R<List<VoiceData>> getVoiceList();
//
//}
