package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        log.info("Mono0");
        getName();
        log.info("Mono0 wait");
        Util.sleepSeconds(1);
        log.info("Mono1");
        Mono.fromFuture(getName());
        log.info("Mono1 wait");
        Util.sleepSeconds(1);
        log.info("Mono2");
        Mono.fromFuture(Lec08MonoFromFuture::getName);
        log.info("Mono2 wait");
        Util.sleepSeconds(1);
        log.info("Mono3");
        Mono.fromFuture(Lec08MonoFromFuture::getName)
                .subscribe(Util.subscriber("sub1"));
        log.info("Mono3 wait");
        Util.sleepSeconds(1);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
           log.info("generating name");
           return Util.faker().name().firstName();
        });
    }

}
