package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {

    public static void main(String[] args) {

        List<String> list = List.of("a", "b", "c");
        Flux.fromIterable(list).subscribe(Util.subscriber("sub1"));

        Integer[] arrInt = {1, 2, 3, 4, 5, 6};
        Flux.fromArray(arrInt).subscribe(Util.subscriber("sub2"));
    }
}
