package com.eric.sec13;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Lec03ContextPropagation {

    private static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {

        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("user", "eric"))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(2);
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("user not authenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.deferContextual(ctx -> {
            log.info("producer1 ctx: {}", ctx);
            return Mono.just("producer1");
        })
                .cast(String.class)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2() {
        return Mono.deferContextual(ctx -> {
                    log.info("producer2 ctx: {}", ctx);
                    return Mono.just("producer2");
                })
                .cast(String.class)
                .subscribeOn(Schedulers.parallel());
    }
}
