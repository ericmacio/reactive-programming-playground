package com.eric.sec05;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(12);

    }
}
