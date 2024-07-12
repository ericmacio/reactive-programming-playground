package com.eric.sec03.assignement;

import com.eric.common.Util;
import com.eric.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAssignment {

    private static final Logger log = LoggerFactory.getLogger(TestAssignment.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        client.getStocks()
                .subscribe(new StockSubscriberImpl("sub1"));
        client.getStocks()
                .subscribe(new StockSubscriberImpl("sub2"));
        Util.sleepSeconds(20);
        log.info("End");
    }
}

