package com.eric.sec06;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {

        Flux<String> movieFlux1 = movieStream();
        Util.sleepSeconds(1);
        movieFlux1.subscribe(Util.subscriber("sub1-1"));
        Util.sleepSeconds(3);
        movieFlux1.subscribe(Util.subscriber("sub1-2"));
        Util.sleepSeconds(15);

        Flux<String> movieFlux2 = movieStream().share();
        //Flux<String> movieFlux2 = movieStream().publish().refCount(1);
        Util.sleepSeconds(1);
        movieFlux2.subscribe(Util.subscriber("sub2-1"));
        Util.sleepSeconds(3);
        movieFlux2.subscribe(Util.subscriber("sub2-2"));
        Util.sleepSeconds(15);

        Flux<String> movieFlux3 = movieStream().publish().refCount(2);
        Util.sleepSeconds(1);
        movieFlux3
                .take(2)
                .subscribe(Util.subscriber("sub3-1"));
        Util.sleepSeconds(3);
        movieFlux3.subscribe(Util.subscriber("sub3-2"));
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
