package com.eric.sec01.publisher;


import com.eric.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublisherImpl implements Publisher<String> {

    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        var subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
