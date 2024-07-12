package com.eric.sec05.assignment;

import com.eric.common.Util;

public class TestAssignment {


    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        for(int i=1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber("sub1"));
        }
        Util.sleepSeconds(5);
    }
}
