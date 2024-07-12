package com.eric.test.common;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class BuildFlux {

    public static Flux<String> generateNames() {
        return Flux.generate(sink -> {
                    sink.next(Util.faker().name().firstName());
                })
                .delayElements(Duration.ofSeconds(1)) //Delay send of item for parallel subscription
                .cast(String.class);
    }
}
