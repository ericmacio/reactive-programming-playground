package com.eric.test.subscription;

import com.eric.test.common.BuildFlux;
import com.eric.test.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


public class EricFluxSharedWithMultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(EricFluxSharedWithMultipleSubscribers.class);

    public static void main(String[] args) {

        Flux<String> flux1 = BuildFlux.generateNames().share();

        flux1
                .take(20)
                .subscribe(Util.subscriber("sub1-share"));
        Util.sleepSeconds(3);
        flux1
                .take(20)
                .subscribe(Util.subscriber("sub2-share"));
        flux1
                .take(20)
                .subscribe(Util.subscriber("sub3-share"));

        Util.sleepSeconds(25);
        log.info("End");

    }

}
