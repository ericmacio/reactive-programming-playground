package com.eric.sec13.client;

import com.eric.common.AbstractHttpClient;
import com.eric.sec11.client.ClientError;
import com.eric.sec11.client.NotFoundError;
import com.eric.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Mono<String> getBook() {
        return this.httpClient
                .get()
                .uri("/demo07/book")
                .response(this::toResponse)
                .startWith(RateLimiter.limitCalls())
                .contextWrite(UserService.userCategoryContext())
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
