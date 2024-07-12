package com.eric.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import java.time.Duration;

public class Lec07ScenarioNameTest {

    private static final Logger log = LoggerFactory.getLogger(Lec07ScenarioNameTest.class);

    //Method from service class
    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }

    @Test
    public void test1() {
        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test");
        StepVerifier.create(getItems(), options)
                .expectNext(1)
                .as("first item should be 1")
                .expectNext(2, 3)
                .as("then 2 and 3")
                .expectComplete()
                .verify();
    }

}
