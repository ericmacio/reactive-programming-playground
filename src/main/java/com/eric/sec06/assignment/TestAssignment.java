package com.eric.sec06.assignment;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

public class TestAssignment {

    private static final Logger log = LoggerFactory.getLogger(TestAssignment.class);

    public static void main(String[] args) {

        log.info("Create flux");
        Flux<Order> fluxOrders = new Orders().getFlux();
        inventoryService(fluxOrders).subscribe(Util.subscriber("quantity"));
        revenueService(fluxOrders).subscribe(Util.subscriber("revenue"));
        log.info("Wait for a while");
        Util.sleepSeconds(10);
    }

    private static Flux<Map<String, Integer>> revenueService(Flux<Order> fluxOrders) {
        Map<String, Integer> revenuePerCategory = new HashMap<>();
        return fluxOrders
                .handle((order, sink) -> {
                    String category = order.getCategory();
                    Integer price = order.getPrice();
                    Integer currRevenue = revenuePerCategory.getOrDefault(category, 0);
                    Integer newRevenue = currRevenue + price;
                    revenuePerCategory.put(category, newRevenue);
                    sink.next(revenuePerCategory);
                });
    }

    private static Flux<Map<String, Integer>> inventoryService(Flux<Order> fluxOrders) {
        final int INIT_QUANTITY_PER_CATEGORY = 500;
        Map<String, Integer> quantityPerCategory = new HashMap<>();
        return fluxOrders
                .handle((order, sink) -> {
                    String category = order.getCategory();
                    Integer quantity = order.getQuantity();
                    Integer currQuantity = quantityPerCategory.getOrDefault(category, INIT_QUANTITY_PER_CATEGORY);
                    Integer newQuantity = currQuantity - quantity;
                    quantityPerCategory.put(category, newQuantity);
                    sink.next(quantityPerCategory);
                });
    }

}
