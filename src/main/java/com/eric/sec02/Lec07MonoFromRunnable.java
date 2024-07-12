package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec07MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {

        getProductName(1)
                .subscribe(Util.subscriber("sub1"));
        getProductName(2)
                .subscribe(Util.subscriber("sub2"));


    }

    private static Mono<String> getProductName(int productId) {
        if(productId == 1) {
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId) {
        log.info("notifying business on unavailable product {}", productId);
    }
}
