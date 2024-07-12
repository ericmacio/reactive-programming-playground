package com.eric.test;

import com.eric.common.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Objects;

public class Lec06VirtualTimeTest {

    private static final Logger log = LoggerFactory.getLogger(Lec06VirtualTimeTest.class);

    //Method from service class
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    @Test
    public void badTest() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    public void test1() {
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    public void test2() {
        StepVerifier.withVirtualTime(this::getItems)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

}
