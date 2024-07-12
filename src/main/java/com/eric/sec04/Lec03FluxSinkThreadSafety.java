package com.eric.sec04;

import com.eric.common.Util;
import com.eric.sec03.assignement.TestAssignment;
import com.eric.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {

        demo1(); //Not threadsafe
        demo2();

    }

    private static void demo1() {
        List<Integer> list = new ArrayList<>();

        Runnable runnable = () -> {
            for(int i=0; i<1000; i++) {
                list.add(i);
            }
        };
        for(int i=0; i<10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(10);
        log.info("list size: {}", list.size());
    }

    private static void demo2() {
        List<String> list = new ArrayList<>();
        NameGenerator generator = new NameGenerator();
        Flux<String> flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for(int i=0; i<1000; i++) {
                generator.generate();
            }
        };
        for(int i=0; i<10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(10);
        log.info("list size: {}", list.size());
    }

}
