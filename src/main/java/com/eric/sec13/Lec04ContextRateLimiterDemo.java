package com.eric.sec13;

import com.eric.common.Util;
import com.eric.sec13.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec04ContextRateLimiterDemo.class);

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        for(int i = 0; i < 20; i++) {
            client.getBook()
                    //.contextWrite(Context.of("category", "prime")) //will be blocked by new ctx category if user or by empty if no user key
                    .contextWrite(Context.of("user", "sam"))
                    .subscribe(Util.subscriber("sub1"));
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(2);
    }


}
