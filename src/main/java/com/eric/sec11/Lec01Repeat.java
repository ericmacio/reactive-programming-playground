package com.eric.sec11;

import com.eric.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {

    /*
        Use case: transform a mono to flux
     */
    public static void main(String[] args) {

        demo5();

        Util.sleepSeconds(15);
    }

    private static void demo1() {
        getCountryName()
                .repeat(3)
                .subscribe(Util.subscriber("demo1"));
    }

    private static void demo2() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeat(() -> atomicInteger.incrementAndGet() < 5)
                .subscribe(Util.subscriber("demo2"));
    }

    private static void demo3() {
        getCountryName()
                .repeat()
                .takeUntil("france"::equalsIgnoreCase)
                .subscribe(Util.subscriber("demo3"));
    }

    private static void demo4() {
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2))).take(5)
                .subscribe(Util.subscriber("demo4"));
    }

    private static void demo5() {
        Flux.just(1, 2, 3)
                .repeat(3)
                .subscribe(Util.subscriber("demo5"));
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
