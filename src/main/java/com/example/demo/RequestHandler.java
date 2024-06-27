package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.BiFunction;

@Component
public class RequestHandler {

    public HandlerFunction<ServerResponse> handle(BiFunction<Integer, Integer, Integer> opLogic) {
        return request -> {
            var n1 = Integer.parseInt(request.pathVariable("n1"));
            var n2 = Integer.parseInt(request.pathVariable("n2"));
            var result = opLogic.apply(n1, n2);
            return ServerResponse.ok().bodyValue(new ResponseDto(result));
        };
    }
}
