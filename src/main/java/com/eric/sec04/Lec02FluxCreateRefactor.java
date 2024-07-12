package com.eric.sec04;

import com.eric.common.Util;
import com.eric.sec04.assignment.TestAssignment;
import com.eric.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    private static final Logger log = LoggerFactory.getLogger(Lec02FluxCreateRefactor.class);

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        Flux flux = Flux.create(generator);
        flux.subscribe(Util.subscriber("sub1")); //Will not receive anything
        flux.subscribe(Util.subscriber("sub2"));
        for(int i=0; i<10; i++) {
            generator.generate();
        }
        log.info("end");
    }
}
