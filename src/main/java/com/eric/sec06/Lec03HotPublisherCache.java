package com.eric.sec06;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherCache.class);

    public static void main(String[] args) {

        Flux<Integer> stockFlux1 = stockStream().replay(1).autoConnect(0);

        Util.sleepSeconds(3);
        log.info("sam is joining");
        stockFlux1
                .subscribe(Util.subscriber("sam-1"));

        Util.sleepSeconds(3);
        log.info("mike is joining");
        stockFlux1.subscribe(Util.subscriber("mike-1"));

        Util.sleepSeconds(15);
        log.info("End");


    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(
                sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(1))
                .cast(Integer.class);
    }
}
