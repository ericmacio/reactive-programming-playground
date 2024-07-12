package com.eric.sec05;

import com.eric.common.Util;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {

    private static final Logger log = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    public static void main(String[] args) {

        //Use case: database + redis cache
        Flux.range(1, 10)
                .filter(i -> i>11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber("sub1"));

    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 3);
    }
}
