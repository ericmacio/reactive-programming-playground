package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec09PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {
        log.info("getName");
        getName();
        Util.sleepSeconds(1);
        log.info("subscribe to getName");
        getName().subscribe(Util.subscriber("sub1"));
        log.info("End of main");
    }

    private static Mono<String> getName() {
        log.info("Create publisher");
        return Mono.fromSupplier(() -> {
            log.info("Execute publisher");
            Util.sleepSeconds(3);
            log.info("End of publisher sleep");
            return Util.faker().name().firstName();
        });
    }

}
