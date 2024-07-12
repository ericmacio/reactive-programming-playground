package com.eric.sec03;

import com.eric.common.Util;
import com.eric.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec08NonBlockingStreamMessages {

    private static final Logger log = LoggerFactory.getLogger(Lec08NonBlockingStreamMessages.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        client.getNames()
                .subscribe(Util.subscriber("sub1"));
        client.getNames()
                .subscribe(Util.subscriber("sub2"));
        log.info("Wait a while ...");
        Util.sleepSeconds(6);
        log.info("End");
    }
}
