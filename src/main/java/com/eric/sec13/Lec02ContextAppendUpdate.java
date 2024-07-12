package com.eric.sec13;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {

    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {

        log.info("Append");
        append();
        log.info("Replace");
        replace();
        log.info("Delete");
        delete();

    }

    private static void delete() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.delete("a"))
                .contextWrite(Context.of("a", "b").put("country", "france"))
                .contextWrite(Context.of("user", "eric"))
                .subscribe(Util.subscriber("sub1"));
    }

    private static void replace() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(ctx -> Context.of("user", "mike"))
                //.contextWrite(ctx -> Context.empty())
                .contextWrite(Context.of("a", "b").put("country", "france"))
                .contextWrite(Context.of("user", "eric"))
                .subscribe(Util.subscriber("sub1"));
    }

    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b").put("country", "france"))
                .contextWrite(Context.of("user", "eric"))
                .subscribe(Util.subscriber("sub1"));
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            log.info("ctx: {}", ctx);
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("user not authenticated"));
        });
    }
}
