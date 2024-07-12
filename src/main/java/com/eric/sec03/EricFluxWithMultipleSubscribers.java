package com.eric.sec03;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class EricFluxWithMultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(EricFluxWithMultipleSubscribers.class);

    public static void main(String[] args) {

        Flux<String> flux1 = generateNames();

        flux1
                .take(20)
                .subscribe(Util.subscriber("sub1-share"));
        flux1
                .take(20)
                .subscribe(Util.subscriber("sub2-share"));
        flux1
                .take(20)
                .subscribe(Util.subscriber("sub3-share"));

        Util.sleepSeconds(25);
        log.info("End");

    }

    private static Flux<String> generateNames() {
        return Flux.generate(sink -> {
                    sink.next(Util.faker().name().firstName());
                })
                .delayElements(Duration.ofSeconds(1)) //Delay send of item for parallel subscription
                .cast(String.class);
    }
}
