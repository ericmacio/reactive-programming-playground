package com.eric.sec04;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    public static void main(String[] args) {

//        fluxUntil1();
//        fluxUntil2();
        fluxUntil3();
        fluxUntil4();


    }

    private static void fluxUntil1() {
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            if(country.equalsIgnoreCase("france")) {
                synchronousSink.complete();
            }
            //synchronousSink.next(2);
            //synchronousSink.complete();
        }).subscribe(Util.subscriber("sub1"));
    }

    private static void fluxUntil2() {
        Flux<String> flux = Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            //synchronousSink.next(2);
            //synchronousSink.complete();
        });
        flux
                .takeUntil("portugal"::equalsIgnoreCase)
                .subscribe(Util.subscriber("sub2"));
    }

    private static void fluxUntil3() {
        Flux.range(1, 10)
                .takeUntil(i -> i<6)
                .subscribe(Util.subscriber("sub3"));
    }

    private static void fluxUntil4() {
        Flux.range(1, 10)
                .takeUntil(i -> i == 6)
                .subscribe(Util.subscriber("sub4"));
    }
}
