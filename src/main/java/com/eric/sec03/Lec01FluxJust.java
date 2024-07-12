package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);
        flux.subscribe(Util.subscriber("sub1"));
        flux
                .filter(i -> i != 4)
                .subscribe(Util.subscriber("sub2"));
        flux
                .map(i -> "eric" + i)
                .subscribe(Util.subscriber("sub3"));

    }

}
