package com.eric.sec09.assignment;

import com.eric.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<Product> getProductInfo(int productId) {
        return Mono.zip(getProductName(productId), getPrice(productId), getReview(productId))
                .map(t -> new Product(t.getT1(), t.getT2(), t.getT3()));
    }

    private Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo05/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    private Mono<String> getPrice(int productId) {
        return this.httpClient.get()
                .uri("/demo05/price/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    private Mono<String> getReview(int productId) {
        return this.httpClient.get()
                .uri("/demo05/review/" + productId)
                .responseContent()
                .asString()
                .next();
    }

}
