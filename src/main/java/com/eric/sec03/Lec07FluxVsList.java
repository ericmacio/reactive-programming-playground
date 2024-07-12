package com.eric.sec03;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import com.eric.sec03.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Lec07FluxVsList {

    private static final Logger log = LoggerFactory.getLogger(Lec07FluxVsList.class);

    public static void main(String[] args) {

        log.info("creating list ...");
        List<String> list = NameGenerator.getNamesList(10);
        log.info("List {}: ", list);

        log.info("creating flux 1 ...");
        NameGenerator.getNamesFlux(10)
                .subscribe(Util.subscriber("sub1"));

        log.info("creating flux 2 ...");
        SubscriberImpl subscriber = new SubscriberImpl();
        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);
        log.info("request 3");
        subscriber.getSubscription().request(3);
        log.info("request 3");
        subscriber.getSubscription().request(3);
        log.info("request 10");
        subscriber.getSubscription().request(10);

    }
}
