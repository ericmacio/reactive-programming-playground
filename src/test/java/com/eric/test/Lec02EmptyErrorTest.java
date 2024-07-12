package com.eric.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {

    private static final Logger log = LoggerFactory.getLogger(Lec02EmptyErrorTest.class);

    //Method from service class
    public static Mono<String> getUserName(int userId) {
        return switch(userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new IllegalArgumentException("Invalid userId"));
        };
    }

    @Test
    public void userTest() {
        StepVerifier.create(getUserName(1))
                .expectNext("sam")
                .expectComplete()
                .verify();
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUserName(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest0() {
        StepVerifier.create(getUserName(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorTest1() {
        StepVerifier.create(getUserName(3))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void errorTest2() {
        StepVerifier.create(getUserName(3))
                .expectErrorMessage("Invalid userId")
                .verify();
    }

    @Test
    public void errorTest3() {
        StepVerifier.create(getUserName(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(IllegalArgumentException.class, ex.getClass());
                    Assertions.assertEquals("Invalid userId", ex.getMessage());
                })
                .verify();
    }
}
