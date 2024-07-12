package com.eric.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {

    private static final Logger log = LoggerFactory.getLogger(Lec08ContextTest.class);

    //Method from service class
    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if(ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("user not authenticated"));
        });
    }

    @Test
    public void test1() {
        var options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));
        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome sam")
                .expectComplete()
                .verify();
    }

    @Test
    public void test2() {
        var options = StepVerifierOptions.create().withInitialContext(Context.empty());
        StepVerifier.create(getWelcomeMessage(), options)
                .expectErrorMessage("user not authenticated")
                .verify();
    }

}
