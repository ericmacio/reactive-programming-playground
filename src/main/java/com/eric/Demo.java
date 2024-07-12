package com.eric;

import com.eric.sec01.publisher.PublisherImpl;
import com.eric.sec01.subscriber.SubscriberImpl;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        demo1();
        demo2();
        demo3();
    }

    private static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2);
        subscriber.getSubscription().request(3);
        Thread.sleep(2);
        subscriber.getSubscription().request(3);
        Thread.sleep(2);
        subscriber.getSubscription().request(3);
        Thread.sleep(2);
        subscriber.getSubscription().request(3);
        Thread.sleep(2);
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2);
        subscriber.getSubscription().request(15);
    }

}