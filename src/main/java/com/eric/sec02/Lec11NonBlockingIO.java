package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import com.eric.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();

        log.info("starting");

        for(int i = 1; i <= 10; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber("sub1"));
        }
        log.info("Request has been sent. Wait for 2 seconds");
        Util.sleepSeconds(2);
    }
}
