package com.eric.sec04;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {

    public static void main(String[] args) {

        Flux.generate(
                () -> 0,
                (count, synchronousSink) -> {
                    count++;
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("france")) {
                        synchronousSink.complete();
                    }
                    return count;
                },
                count -> System.out.println("count: " + count)
                ).subscribe(Util.subscriber("sub1"));

    }
}
