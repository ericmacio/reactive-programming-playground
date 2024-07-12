package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {

    public static void main(String[] args) {

        Flux<String> firstNameStream = generateFirstNameStream().share();
        firstNameStream
                .subscribe(Util.subscriber("sub1"));
        firstNameStream
                .subscribe(Util.subscriber("sub2"));

        generateLastNameStream()
                .subscribe(Util.subscriber("sub3"));

        Util.sleepSeconds(5);
    }

    private static Flux<String> generateFirstNameStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().name().firstName());
    }

    private static Flux<String> generateLastNameStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().name().lastName());
    }
}
