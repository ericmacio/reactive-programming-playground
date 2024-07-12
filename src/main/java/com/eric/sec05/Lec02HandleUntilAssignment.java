package com.eric.sec05;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {

    public static void main(String[] args) {

        Flux.<String>generate(sink -> sink.next(Util.faker().country().name()))
                .handle((item, sink) -> {
                    sink.next(item);
                    if(item.equalsIgnoreCase("france")) {
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber("sub1"));
    }

}
