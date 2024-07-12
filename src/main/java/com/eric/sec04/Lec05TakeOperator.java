package com.eric.sec04;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Lec05TakeOperator {

    private static final Logger log = LoggerFactory.getLogger(Lec05TakeOperator.class);

    public static void main(String[] args) {

        take();
        takeWhile();
        takeUntil();


    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber("sub1"));
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .takeWhile(i -> i <= 5) //Stop when condition is no longer met
                .subscribe(Util.subscriber("sub2"));
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .takeUntil(i -> i == 8) //Stop after condition has been met
                .subscribe(Util.subscriber("sub3"));
    }
}
