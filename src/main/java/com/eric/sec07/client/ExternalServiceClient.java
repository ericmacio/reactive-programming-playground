package com.eric.sec07.client;

import com.eric.common.AbstractHttpClient;
import com.eric.sec07.Lec05PublishOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(s -> log.info("next: {}", s))
                .next();
    }

}
