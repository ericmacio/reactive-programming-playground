package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {

    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {

        List<String> records = List.of("a", "b", "c");

        saveRecords(records)
                .then()
                .subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(3);

        saveRecords(records)
                .then(sendNotification(records))
                .subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(3);

    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved " + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> log.info("all these {} records saved successfully", records));
    }
}
