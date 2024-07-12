package com.eric.sec02;

import com.eric.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("vins");
        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
        save(Mono.just("Hello"));
    }

    private static void save(Publisher<String> publisher) {
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(10);
    }
}
