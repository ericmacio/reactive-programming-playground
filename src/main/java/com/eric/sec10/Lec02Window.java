package com.eric.sec10;

import com.eric.common.DefaultSubscriberImpl;
import com.eric.common.Util;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec02Window {

    private static final Logger log = LoggerFactory.getLogger(Lec02Window.class);

    public static void main(String[] args) {

        demo1();
        demo2();
        Util.sleepSeconds(15);
        System.out.println("\nEnd");

    }

    private static void demo1() {
        DefaultSubscriberImpl<Void> sub = new DefaultSubscriberImpl<>("demo1");
        eventStream()
                .window(5)
                .flatMap(Lec02Window::processEvents)
                .subscribe(sub);
        Util.sleepSeconds(15);
        sub.getSubscription().cancel();
        System.out.println("\nEnd of demo1");
    }

    private static void demo2() {
        eventStream()
                .window(Duration.ofMillis(800))
                .flatMap(Lec02Window::processEvents)
                .subscribe(Util.subscriber("demo2"));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux.doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event-" + i);
    }


}
