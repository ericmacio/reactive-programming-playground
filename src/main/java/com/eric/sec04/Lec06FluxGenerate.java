package com.eric.sec04;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
            //synchronousSink.next(2);
            //synchronousSink.complete();
        })
                .take(4)
                .subscribe(Util.subscriber("sub1"));

    }
}
