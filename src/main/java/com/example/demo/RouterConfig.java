package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    @Autowired
    private RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> router() {
        return RouterFunctions.route()
                .path("/calculator", this::calculatorRoutes)
                .build();
    }

    private RouterFunction<ServerResponse> calculatorRoutes() {
        return RouterFunctions.route()
                .GET("/{n1}/{n2}", is("+"), requestHandler.handle((n1, n2) -> n1 + n2))
                .GET("/{n1}/{n2}", is("-"), requestHandler.handle((n1, n2) -> n1 - n2))
                .GET("/{n1}/{n2}", is("*"), requestHandler.handle((n1, n2) -> n1 * n2))
                .GET("/{n1}/0", req -> ServerResponse.badRequest().bodyValue(new ResponseDto("can not divide by zero")))
                .GET("/{n1}/{n2}", is("/"), requestHandler.handle((n1, n2) -> n1 / n2))
                .GET("/{n1}/{n2}", req -> ServerResponse.badRequest().bodyValue(new ResponseDto("invalid operator")))
                .build();
    }

    private RequestPredicate is(String operator) {
        return RequestPredicates.headers(h -> operator.equals(h.firstHeader("OP")));
    }
}
