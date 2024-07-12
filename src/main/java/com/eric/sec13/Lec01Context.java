package com.eric.sec13;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {

    private static final Logger log = LoggerFactory.getLogger(Lec01Context.class);

    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(Context.of("user", "eric"))
                .subscribe(Util.subscriber("sub1"));
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("user not authenticated"));
        });
    }
}
