package com.eric.sec07;

import com.eric.common.Util;
import com.eric.sec07.client.ExternalServiceClient;
import com.eric.sec07.client.ExternalServiceClientWithFix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06EventLoopIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        for(int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber("sub1"));
        }
        Util.sleepSeconds(10);
        log.info("End of first try)");

        ExternalServiceClientWithFix clientWithFix = new ExternalServiceClientWithFix();
        for(int i = 1; i <= 5; i++) {
            clientWithFix.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber("sub2"));
        }
        Util.sleepSeconds(5);
        log.info("End of fix");
    }

    private static String process(String input) {
        Util.sleepSeconds(1);
        return input + "-process";
    }

}
