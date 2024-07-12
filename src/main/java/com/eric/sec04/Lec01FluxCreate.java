package com.eric.sec04;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    private static final Logger log = LoggerFactory.getLogger(Lec01FluxCreate.class);

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.complete();
        }).subscribe(Util.subscriber("sub1"));

        Flux<String> flux = Flux.create(fluxSink -> {
            for(int i=0; i<5; i++) {
                String name = Util.faker().country().name();
                log.info("generating name: {}", name);
                fluxSink.next(name);
            }
        });
        flux.subscribe(Util.subscriber("sub2"));

        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while(!country.equalsIgnoreCase("france"));
            fluxSink.complete();
        }).subscribe(Util.subscriber("sub3"));

    }
}
