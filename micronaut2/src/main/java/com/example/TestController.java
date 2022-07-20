package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/test")
public class TestController {
    @Get
    Mono<HttpResponse<String>> get() {
        Flux<Boolean> bools = Flux.empty();
        for (int i = 0; i < 100; i++) {
            bools = bools.concatWith(Mono.just(true));
        }
        return bools.reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2).map(bool -> HttpResponse.ok(bool.toString()));
    }

}
