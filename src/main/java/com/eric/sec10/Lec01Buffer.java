package com.eric.sec10;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    private static final Logger log = LoggerFactory.getLogger(Lec01Buffer.class);

    public static void main(String[] args) {

        demo3();

        Util.sleepSeconds(20);
    }

    private static void demo1() {
        eventStream()
                .buffer()
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo2() {
        eventStream()
                .buffer(3)
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo4() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber("sub1"));
    }


    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                //.take(10) For demo4
                //.concatWith(Flux.never()) for demo4
                .map(i -> "event-" + i);
    }


}
