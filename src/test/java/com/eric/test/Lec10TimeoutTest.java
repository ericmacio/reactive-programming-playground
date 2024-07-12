package com.eric.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Lec10TimeoutTest {

    private static final Logger log = LoggerFactory.getLogger(Lec10TimeoutTest.class);

    //Method from service class
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
    }

    @Test
    public void test1() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify(Duration.ofMillis(1500));
    }

}
