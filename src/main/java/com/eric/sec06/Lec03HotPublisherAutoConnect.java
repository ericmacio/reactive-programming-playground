package com.eric.sec06;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublisherAutoConnect {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {

        //Flux<String> movieFlux1 = movieStream().publish().autoConnect();
        Flux<String> movieFlux1 = movieStream().publish().autoConnect(0);
        Util.sleepSeconds(3);
        movieFlux1
                .take(4)
                .subscribe(Util.subscriber("sub1-1"));
        Util.sleepSeconds(3);
        movieFlux1.subscribe(Util.subscriber("sub1-2"));
        Util.sleepSeconds(15);

        log.info("End");


    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                () -> {
                    log.info("received the request");
                    return 1;
                },
                (state, sink) -> {
                    String scene = "movie scene " + state;
                    log.info("playing {}", scene);
                    sink.next(scene);
                    return ++state;
                }
        )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
