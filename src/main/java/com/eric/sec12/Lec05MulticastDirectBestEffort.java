package com.eric.sec12;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec05MulticastDirectBestEffort {

    private static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {

        demo2();

    }

    private static void demo1() {
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Integer> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();
        flux
                .subscribe(Util.subscriber("sub1"));
        flux
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("sub2"));
        for(int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }

    private static void demo2() {
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Integer> sink = Sinks.many().multicast().directBestEffort();
        Flux<Integer> flux = sink.asFlux();
        flux
                .subscribe(Util.subscriber("sub1"));
        flux
                .onBackpressureBuffer()
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("sub2"));
        for(int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
        Util.sleepSeconds(30);
    }
}
