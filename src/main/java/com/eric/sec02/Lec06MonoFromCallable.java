package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {

        var list = List.of(1,2,3);
        System.out.println("Mono1");
        Mono.fromCallable(() -> sum(list));
        System.out.println("Mono2");
        Mono.fromCallable(() -> sum(list))
                        .subscribe(Util.subscriber("MySubMono2"));

    }

    private static int sum(List<Integer> list) throws Exception{
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
