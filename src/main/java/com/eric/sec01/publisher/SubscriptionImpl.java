package com.eric.sec01.publisher;

import com.eric.sec01.subscriber.SubscriberImpl;
import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseMotionAdapter;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private static final int MAX_ITEMS = 10;
    private final Faker faker;
    private Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if(isCancelled) {
            return;
        }
        log.info("Subscriber has requested {} items", requested);
        if(requested > MAX_ITEMS) {
            this.subscriber.onError((new RuntimeException("Validation failed")));
            this.isCancelled = true;
            return;
        }
        for(int i = 0; i<  requested && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }
        if(count == MAX_ITEMS) {
            log.info("no more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("Subscriber has cancelled");
        isCancelled = true;
    }
}
