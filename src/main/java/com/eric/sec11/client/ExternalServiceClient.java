package com.eric.sec11.client;

import com.eric.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);


    public Mono<String> getProductName(int productId) {
        return get("/demo06/product/" + productId);
    }

    public Mono<String> getCountry() {
        return get("/demo06/country");
    }

    public Mono<String> get(String path) {
        return this.httpClient
                .get()
                .uri(path)
                .response(this::toResponse)
                .next();
    }

    private Flux<String> toResponse(HttpClientResponse httpClientResponse, ByteBufFlux byteBufFlux) {
        log.info("HTTP status code {}", httpClientResponse.status().code());
        return switch (httpClientResponse.status().code()) {
            case 200 -> byteBufFlux.asString();
            case 400 -> Flux.error(new ClientError());
            case 404 -> Flux.error(new NotFoundError());
            default -> Flux.error(new ServerError());
        };
    }

}
