package com.eric.sec05;

import com.eric.common.Util;
import com.eric.sec08.Lec04FluxCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    private static final Logger log = LoggerFactory.getLogger(Lec01Handle.class);

    public static void main(String[] args) {

        Flux<Integer> flux1 = Flux.range(1, 10);
        Flux<Integer> flux2 = flux1.handle((item, sink) -> {
                    sink.error(new RuntimeException("oops"));
                });
        flux1.subscribe(Util.subscriber("sub1"));
        flux2.subscribe(Util.subscriber("sub2"));

        Flux<Integer> flux3 = Flux.range(1, 10)
                .filter(i -> i!= 7)
                .handle((item, sink) -> {
                    log.info("item: {}", item);
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                })
                .cast(Integer.class);
        flux3.subscribe(Util.subscriber("sub3"));
    }
}
