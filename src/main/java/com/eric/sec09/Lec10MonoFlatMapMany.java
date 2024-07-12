package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.applications.OrderService;
import com.eric.sec09.applications.PaymentService;
import com.eric.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {

        /*
            Get the orders of userName
         */

        UserService.getUserId("sam")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(3);
    }
}
