package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec02StartWithUseCase {

    private static final Logger log = LoggerFactory.getLogger(Lec02StartWithUseCase.class);

    public static void main(String[] args) {

        NameGenerator nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(5)
                .subscribe(Util.subscriber("sub1"));
        nameGenerator.generateNames()
                .take(10)
                .subscribe(Util.subscriber("sub2"));
        nameGenerator.generateNames()
                .take(15)
                .subscribe(Util.subscriber("sub3"));

    }
}
