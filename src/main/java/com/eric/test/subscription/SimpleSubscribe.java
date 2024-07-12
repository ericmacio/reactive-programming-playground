package com.eric.test.subscription;

import com.eric.test.common.BuildFlux;
import com.eric.test.common.User;
import com.eric.test.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class SimpleSubscribe {

    private static final Logger log = LoggerFactory.getLogger(SimpleSubscribe.class);

    public static void main(String[] args) {

        User user1 = new User("user1");
        User user2 = new User("user2");
        Flux<String> flux = BuildFlux.generateNames().share();
        flux
                .take(10)
                .subscribe(user1::receiveMsg);
        flux
                .take(10)
                .subscribe(user2::receiveMsg);

        Util.sleepSeconds(12);

    }

}
