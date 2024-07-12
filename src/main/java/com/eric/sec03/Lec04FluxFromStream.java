package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        Flux<Integer> flux1 = Flux.fromStream(list.stream());
        flux1.subscribe(Util.subscriber("sub1"));
        //flux.subscribe(Util.subscriber("sub2")); //Stream has been already consumed
        Flux<Integer> flux2 = Flux.fromStream(list::stream);
        flux2.subscribe(Util.subscriber("sub3"));
        flux2.subscribe(Util.subscriber("sub4"));



    }
}
