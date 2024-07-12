package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.applications.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec16Assignment {

    /*
        Get all users and build 1 object as shown here
        record UserInformation(Integer userId, String userName, Integer balance, List<Order> orders) {}
     */

    private static final Logger log = LoggerFactory.getLogger(Lec16Assignment.class);
    record UserInformation(Integer userId, String userName, Integer balance, List<Order> orders) {}

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(2);

    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrders(user.id()).collectList()
        )
                .map(t -> new UserInformation(user.id(), user.userName(), t.getT1(), t.getT2()));
    }

}
