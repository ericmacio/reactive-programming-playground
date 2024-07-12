package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {

    public static void main(String[] args) {

        Flux.empty()
                .subscribe(Util.subscriber("sub1"));
        Flux.error(new RuntimeException("oops"))
                .subscribe(Util.subscriber("sub2"));
    }
}
