package com.eric.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {

    public static Flux<Flight> getFlights() {
        return Flux.merge(
                AmericanAirlines.getFlights(),
                Qatar.getFlights(),
                Emirates.getFlights()
        )
                .take(Duration.ofSeconds(2));
    }
}
