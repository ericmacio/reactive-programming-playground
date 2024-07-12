package com.eric.common;

import com.eric.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriberImpl<T> implements Subscriber<T> {

    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriberImpl.class);
    private Subscription subscription;
    private final String name;
    private int nbItemsReceived = 0;

    public DefaultSubscriberImpl(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        nbItemsReceived += 1;
        log.info("{} received: {}", this.name, item);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} error: {}", this.name, throwable.getMessage());
        displayResult();
    }

    @Override
    public void onComplete() {
        log.info("{} completed", this.name);
        displayResult();
    }

    public Subscription getSubscription() {
        return subscription;
    }

    private void displayResult() {
        log.info("{}: Total items received: {}", this.name, this.nbItemsReceived);
    }

}