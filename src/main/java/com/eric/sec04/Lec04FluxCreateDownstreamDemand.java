package com.eric.sec04;

import com.eric.common.DefaultSubscriberImpl;
import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {

        //produceEarly();
        produceOnDemand();
    }

    private static void produceOnDemand() {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for(int i=0; i<request; i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated on request: {}", name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);

        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
    }

    private static void produceEarly() {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for(int i=0; i<10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);
        //All data have been already put into the fluxSink output queue
        //Subscriber must read data from the queue to empty it

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
    }
}
