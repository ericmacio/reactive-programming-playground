package com.eric.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxTest.class);

    //Method from service class
    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3)
                .log();
    }

    @Test
    public void fluxTest1() {
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    public void fluxTest2() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest3() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

}
