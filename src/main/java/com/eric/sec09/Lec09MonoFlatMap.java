package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.applications.PaymentService;
import com.eric.sec09.applications.UserService;

public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        /*
            Get the balance of userName
         */

        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber("sub1"));
    }
}
