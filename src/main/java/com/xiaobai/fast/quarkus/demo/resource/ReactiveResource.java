package com.xiaobai.fast.quarkus.demo.resource;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.Router;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @date 2023/7/26
 * @since 1.0
 */
@ApplicationScoped
public class ReactiveResource {

    @Route(methods = Route.HttpMethod.GET)
    void hello(RoutingContext routingContext){
        routingContext.response().end("hello reactive route");
    }

    @Route(path = "/world")
    String helloWorld() {
        return "Hello world!";
    }

    @Route(path = "/greetings", methods = Route.HttpMethod.GET)
    void greetings(RoutingExchange ex) {
        ex.ok("hello " + ex.getParam("name").orElse("world"));
    }

    @Route(path = "/baidu", methods = Route.HttpMethod.GET)
    void baidu(RoutingExchange ex) {

    }
}
