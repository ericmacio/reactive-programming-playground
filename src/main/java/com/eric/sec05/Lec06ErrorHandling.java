package com.eric.sec05;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .subscribe(Util.subscriber("sub1"));

        Flux.just(5)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorResume(ArithmeticException.class, ex -> fallback1())
                .onErrorResume(ex -> fallback2())
                .subscribe(Util.subscriber("sub2"));

        Flux.range(1, 10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorContinue((ex, obj) -> log.error("=> {}", obj, ex))
                .subscribe(Util.subscriber("sub3"));
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(1, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 1000));
    }
}
