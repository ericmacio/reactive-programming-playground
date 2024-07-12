package com.eric.sec06;

import com.eric.common.Util;
import com.eric.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06FluxCreateRefactorIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxCreateRefactorIssueFix.class);

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        Flux flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1")); //Will not receive anything
        flux.subscribe(Util.subscriber("sub2"));
        for(int i=0; i<10; i++) {
            generator.generate();
        }
        log.info("end");
    }
}
