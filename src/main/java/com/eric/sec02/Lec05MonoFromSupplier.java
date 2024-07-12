package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {

        var list = List.of(1,2,3);
        log.info("Mono1");
        Mono.just(sum(list));
        log.info("Mono2");
        Mono.fromSupplier(() -> sum(list));
        log.info("Mono3");
        Mono.fromSupplier(() -> sum(list))
                        .subscribe(Util.subscriber("MySubMono3"));
        log.info("Mono4");
        Mono.just(sum(list))
                .subscribe(Util.subscriber("mySubMono4"));

    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
