package com.eric.sec09;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {

    record Car(String body, String engine, String tires, Integer power){}

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTires(), getPower())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3(), t.getT4()))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(5);
    }


    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 7)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "tires-" + i)
                .delayElements(Duration.ofMillis(500));
    }

    private static Flux<Integer> getPower() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500));
    }
}
