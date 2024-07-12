package com.eric.sec10.assignment.groupby;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TestAssignment {

    public static void main(String[] args) {

        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category) .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(30);

    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(50))
                .map(i -> PurchaseOrder.create());
    }
}
