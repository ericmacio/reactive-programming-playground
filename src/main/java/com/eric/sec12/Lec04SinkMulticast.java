package com.eric.sec12;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkMulticast {

    private static final Logger log = LoggerFactory.getLogger(Lec04SinkMulticast.class);

    public static void main(String[] args) {

        warmUpBehavior();
    }

    private static void demo1() {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        sink.tryEmitNext("hello");
        sink.tryEmitNext("bonjour");
        sink.tryEmitNext("hi");
        sink.tryEmitNext("where are you ?");

        Util.sleepSeconds(2);
        flux.subscribe(Util.subscriber("sub3"));
        sink.tryEmitNext("last message");
    }

    private static void warmUpBehavior() {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> flux = sink.asFlux();
        sink.tryEmitNext("hello");
        sink.tryEmitNext("bonjour");
        sink.tryEmitNext("hi");
        sink.tryEmitNext("where are you ?");

        Util.sleepSeconds(2);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        flux.subscribe(Util.subscriber("sub3"));
        sink.tryEmitNext("last message");
    }
}
