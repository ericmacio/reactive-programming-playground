package com.eric.sec11;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    /*
        Use case: transform a mono to flux
     */
    public static void main(String[] args) {

       demo3();

       Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .retry(3)
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry
                            .fixedDelay(6, Duration.ofSeconds(1))
                            .doBeforeRetry(rs -> log.info("retrying {}", rs.totalRetries()))
                )
                .subscribe(Util.subscriber("sub1"));
    }

    private static void demo3() {
        getCountryName()
                .retryWhen(
                        Retry
                            .fixedDelay(6, Duration.ofSeconds(1))
                            .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                )
                .subscribe(Util.subscriber("sub1"));
    }

    private static Mono<String> getCountryName() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if(atomicInteger.incrementAndGet() < 5) {
                throw new RuntimeException("oops");
            }
            return Util.faker().country().name();
        })
                .doOnError(err -> log.info("ERROR: {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("subscribing"));
    }
}
