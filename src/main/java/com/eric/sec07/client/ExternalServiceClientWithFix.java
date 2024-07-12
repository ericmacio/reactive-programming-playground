package com.eric.sec07.client;

import com.eric.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClientWithFix extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClientWithFix.class);

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(s -> log.info("next: {}", s))
                .next()
                .publishOn(Schedulers.boundedElastic());
    }

}
