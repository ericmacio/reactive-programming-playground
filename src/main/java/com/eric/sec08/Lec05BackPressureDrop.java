package com.eric.sec08;


import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec05BackPressureDrop {

    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureDrop.class);

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux<Integer> producer = Flux.create( //No automatic backpressure handling with create
                sink -> {
                    for(int i=0; i<500 && !sink.isCancelled(); i++) {
                        log.info("generating {}", i);
                        sink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    sink.complete();
                }
        )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .onBackpressureDrop()
                //.onBackpressureLatest()
                .log()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackPressureDrop::timeConsumingTask)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(30);
        log.info("End");
    }

    private static int timeConsumingTask(int i) {
        log.info("received: {}", i);
        Util.sleepSeconds(1);
        return i;
    }

}
