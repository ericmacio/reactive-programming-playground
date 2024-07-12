package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec05FluxFromRange {

    public static void main(String[] args) {

        Flux.range(3, 10).subscribe(Util.subscriber("sub1"));

        Flux.range(1, 10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber("sub2"));

    }
}
