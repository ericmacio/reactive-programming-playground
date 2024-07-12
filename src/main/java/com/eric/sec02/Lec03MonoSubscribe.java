package com.eric.sec02;

import com.eric.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {

        Mono<String> mono1 = Mono.just("Hello bob");
        mono1.subscribe(i -> log.info("mono1 received : {}", i),
                err -> log.error("mono1 error: {}", err.getMessage()),
                () -> log.info("mono1 completed"),
                Subscription::cancel);

        Mono<String> mono2 = Mono.just("Hello bobby");
        mono2.subscribe(i -> log.info("mono2 received : {}", i),
                err -> log.error("mono2 error: {}", err.getMessage()),
                () -> log.info("mono2 completed"),
                subscription -> subscription.request(1));

        Mono<String> mono3 = Mono.just("Hello james").map(s -> s + "_add");
        mono3.subscribe(i -> log.info("mono3 received : {}", i),
                err -> log.error("mono3 error: {}", err.getMessage()),
                () -> log.info("mono3 completed"),
                subscription -> subscription.request(1));

        Mono<Integer> mono4 = Mono.just(1).map(i -> i / 0);
        mono4.subscribe(i -> log.info("mono4 received : {}", i),
                err -> log.error("mono4 error: {}", err.getMessage()),
                () -> log.info("mono4 completed"),
                subscription -> subscription.request(1));
    }
}
