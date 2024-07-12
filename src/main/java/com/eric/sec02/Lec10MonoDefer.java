package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec10MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {

        log.info("createPublisher");
        createPublisher();
        log.info("createPublisher + subscribe");
        createPublisher()
                .subscribe(Util.subscriber("MySub1"));
        log.info("defer createPublisher");
        Mono.defer(Lec10MonoDefer::createPublisher);
        log.info("defer createPublisher + subscribe");
        Mono.defer(Lec10MonoDefer::createPublisher) //In case createPublisher is time-consuming
                .subscribe(Util.subscriber("MySub2"));

    }

    private static Mono<Integer> createPublisher() {
        log.info("Creating publisher");
        var list = List.of(1,2,3);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }

}
