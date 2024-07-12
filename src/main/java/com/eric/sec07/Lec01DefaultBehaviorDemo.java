package com.eric.sec07;

import com.eric.common.Util;
import com.eric.sec06.Lec02HotPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehaviorDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.create(sink -> {
            for(int i = 1; i < 3; i++) {
                log.info("Generating {}", i);
                sink.next(i);
            }
            sink.complete();
        })
        .cast(Integer.class)
        .doOnNext(v -> log.info("value: {}", v));

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("subRunnable"));
        Thread.ofPlatform().start(runnable);
    }

}
