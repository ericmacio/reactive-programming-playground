package com.eric.sec07;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec07SubscribeOnPublishOn {

    private static final Logger log = LoggerFactory.getLogger(Lec07SubscribeOnPublishOn.class);

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.create(sink -> {
                for(int i = 1; i < 3; i++) {
                    log.info("Generating {}", i);
                    sink.next(i);
                }
                sink.complete();
            })
                .publishOn(Schedulers.parallel())
                .cast(Integer.class)
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first-1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first-2"));
        flux.subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(2);
        log.info("End");
    }

}
