package com.eric.sec12;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    private static final Logger log = LoggerFactory.getLogger(Lec02SinkUnicast.class);

    public static void main(String[] args) {

        demo1();
    }

    private static void demo1() {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<String> flux = sink.asFlux();
        sink.tryEmitNext("hello");
        sink.tryEmitNext("bonjour");
        sink.tryEmitNext("hi");
        sink.tryEmitNext("where are you ?");
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }

}
