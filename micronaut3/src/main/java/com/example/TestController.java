package com.example;

import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

@Controller("/test")
@Singleton
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
