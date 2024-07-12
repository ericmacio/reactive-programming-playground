package com.eric.sec12;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec03SinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);

    public static void main(String[] args) {

        demoThreadSafe();
    }

    private static void demoNotThreadSafe() {
        Sinks.Many<Integer> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();
        List<Integer> list = new ArrayList<>();
        flux.subscribe(list::add);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i = 1; i <= 50; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                log.info("emit: {}, counter: {}", j, atomicInteger.incrementAndGet());
                sink.tryEmitNext(j);
            });
        }
        Util.sleepSeconds(2);
        log.info("atomic counter: {}, list size: {}", atomicInteger.get(), list.size());
    }

    private static void demoThreadSafe() {
        Sinks.Many<Integer> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = sink.asFlux();
        List<Integer> list = new ArrayList<>();
        flux.subscribe(list::add);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i = 1; i <= 50; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                log.info("emit: {}, counter: {}", j, atomicInteger.incrementAndGet());
                sink.emitNext(j, (signalType, emitResult) -> {
                    log.info("j: {}, signal: {}, result: {}", j, signalType.name(), emitResult.name());
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }
        Util.sleepSeconds(2);
        log.info("atomic counter: {}, list size: {}", atomicInteger.get(), list.size());
    }

}
