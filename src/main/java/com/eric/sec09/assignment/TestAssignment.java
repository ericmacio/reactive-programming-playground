package com.eric.sec09.assignment;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAssignment {

    private static final Logger log = LoggerFactory.getLogger(TestAssignment.class);

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        for(int i=1; i <= 10; i++) {
            client.getProductInfo(i)
                    .subscribe(Util.subscriber("sub1"));
        }
        Util.sleepSeconds(10);

    }

}
