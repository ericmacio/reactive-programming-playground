package com.eric.sec10.assignment.buffer;

import com.eric.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAssignment {

    public static void main(String[] args) {

        Set<String> allowedCategories = Set.of(
                "Science fiction",
                "fantasy",
                "Suspense/Thriller"
        );

        orderStream()
                .filter(o -> allowedCategories.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(TestAssignment::generateReport)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(30);


    }

    private static Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orders) {
        Map<String, Integer> revenue = orders.stream()
                .collect(Collectors.groupingBy(BookOrder::genre, Collectors.summingInt((BookOrder::price))));
        return new RevenueReport(LocalTime.now(), revenue);
    }
}
