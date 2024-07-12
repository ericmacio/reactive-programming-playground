package com.eric.sec10.assignment.window;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAssignment {

    private static final Logger log = LoggerFactory.getLogger(TestAssignment.class);

    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger(0);
        String fileNameFormat = "src/main/resources/sec10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1500))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(20);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event-" + i);
    }


}
