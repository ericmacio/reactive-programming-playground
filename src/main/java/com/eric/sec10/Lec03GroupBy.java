package com.eric.sec10;

import com.eric.common.DefaultSubscriberImpl;
import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03GroupBy {

    private static final Logger log = LoggerFactory.getLogger(Lec03GroupBy.class);

    public static void main(String[] args) {

        Flux.range(1, 15)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2) // 0, 1
                .flatMap(Lec03GroupBy::processEvents)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(20);

    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("received flux for {}", groupedFlux.key());
        return groupedFlux
                .doOnNext((i -> log.info("key: {}, item: {}", groupedFlux.key(), i)))
                .doOnComplete(() -> log.info("{} completed", groupedFlux.key()))
                .then();
    }
}
