package com.eric.sec12;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {

    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {

        demo1();
    }

    private static void demo1() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("sub1"));
        sink.tryEmitValue("hello");
        //sink.tryEmitValue("bonjour");
        //sink.tryEmitEmpty();
        //sink.tryEmitError(new RuntimeException("oops"));
    }

    private static void demo2() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("sub1"));
        mono.subscribe(Util.subscriber("sub2"));
        sink.tryEmitValue("hello");
    }

    private static void demo3() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("sub1"));
        sink.emitValue("hello", (signalType, emitResult) -> {
            log.info("hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        });
        sink.emitValue("bonjour", (signalType, emitResult) -> { //Error handler
            log.info("bonjour");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        });
    }



}
