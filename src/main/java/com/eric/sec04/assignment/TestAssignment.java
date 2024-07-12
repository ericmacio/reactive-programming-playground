package com.eric.sec04.assignment;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class TestAssignment {

    private static final Path PATH = Path.of("src/main/resources/sec02/file.txt");
    private static final Logger log = LoggerFactory.getLogger(TestAssignment.class);

    public static void main(String[] args) {

        SubscriberImpl subscriber = new SubscriberImpl();
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService
                .read(PATH)
                .subscribe(subscriber);
        log.info("Subscription started");
        log.info("Request 1");
        subscriber.getSubscription().request(1);
        log.info("Request 2");
        subscriber.getSubscription().request(2);
        log.info("Request 20");
        subscriber.getSubscription().request(10);

    }
}
