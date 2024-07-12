package com.eric.sec11;

import com.eric.common.Util;
import com.eric.sec11.client.ExternalServiceClient;
import com.eric.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {

        //repeat();
        retry();

        Util.sleepSeconds(20);
    }

    private static void repeat() {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil("france"::equalsIgnoreCase)
                .subscribe(Util.subscriber("demo"));
    }

    private static void retry() {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber("demo"));
    }

    private static Retry retryOnServerError() {
        return Retry
                .fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()));
    }

}
