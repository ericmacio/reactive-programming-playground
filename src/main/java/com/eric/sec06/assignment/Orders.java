package com.eric.sec06.assignment;

import com.eric.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Orders extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(Orders.class);

    public Flux<Order> getFlux() {
        return
                httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                        .map(Orders::getOrder)
                        .publish()
                        .autoConnect(2);
    }

    private static Order getOrder(String item) {
        String[] orderArray = item.split(":");
        return new Order(
                orderArray[0],
                orderArray[1],
                Integer.parseInt(orderArray[2]),
                Integer.parseInt(orderArray[3])
        );
    }


}
