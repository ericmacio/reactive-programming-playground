package com.eric.sec07;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04VirtualThreads {

    private static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        Flux<Integer> flux = Flux.create(sink -> {
                for(int i = 1; i < 3; i++) {
                    log.info("Generating {}", i);
                    sink.next(i);
                }
                sink.complete();
            })
                .cast(Integer.class)
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("thread is virtual: {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first-2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);

        Util.sleepSeconds(2);
        log.info("End");
    }

}
