package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.applications.OrderService;
import com.eric.sec09.applications.User;
import com.eric.sec09.applications.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11FluxFlatMap {

    private static final Logger log = LoggerFactory.getLogger(Lec11FluxFlatMap.class);

    public static void main(String[] args) {

        /*
            Get all the orders from order service
         */

        UserService.getAllUsers()
                .flatMap(user -> OrderService.getUserOrders(user.id()))
                .subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(3);
        log.info("------ End of sub1");

        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrders)
                .subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(3);
        log.info("------ End of sub2");

        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrders, 1) // UserId concurrency parameter
                .subscribe(Util.subscriber("sub3"));
        Util.sleepSeconds(3);
        log.info("------ End of sub3");
    }
}
