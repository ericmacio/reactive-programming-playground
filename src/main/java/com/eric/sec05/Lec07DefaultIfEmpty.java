package com.eric.sec05;

import com.eric.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {

        Mono.empty()
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber("sub1"));

        Flux.range(1, 10)
                .filter(i -> i>11)
                .defaultIfEmpty(999)
                .subscribe(Util.subscriber("sub2"));
    }
}
