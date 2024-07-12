package com.eric.sec03.assignement;

import com.eric.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockSubscriberImpl implements Subscriber<Integer> {

    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;
    private final String name;
    private final int START_BALANCE = 1000;
    private int balance = START_BALANCE;
    private int nbStocks = 0;

    public StockSubscriberImpl(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer stockValue) {
        if(stockValue < 90 && balance >= stockValue) {
            balance -= stockValue;
            nbStocks += 1;
            log.info("{} Stock value: {}, new stock: {}, new balance: {}", name, stockValue, nbStocks, balance);
            this.subscription.request(1);
        } else if(stockValue > 110 && nbStocks > 0) {
            balance = nbStocks * stockValue;
            log.info("{} Stock value: {}, profit: {}", name, stockValue, balance - START_BALANCE);
            nbStocks = 0;
            this.subscription.cancel();
        } else {
            this.subscription.request(1);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} error: {}", this.name, throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("{} completed", this.name);
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
