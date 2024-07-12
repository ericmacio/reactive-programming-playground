package com.eric.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01MonoTest {

    private static final Logger log = LoggerFactory.getLogger(Lec01MonoTest.class);

    //Method from service class
    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier(() -> "product-" + id)
                .doFirst(() -> log.info("invoked"));
    }

    @Test
    public void productTest() {
        StepVerifier.create(getProduct(1))
                .expectNext("product-1")
                .expectComplete()
                .verify();
    }
}
