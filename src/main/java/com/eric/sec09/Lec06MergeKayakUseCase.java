package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.helper.Kayak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06MergeKayakUseCase {

    private static final Logger log = LoggerFactory.getLogger(Lec06MergeKayakUseCase.class);

    public static void main(String[] args) {

        Kayak.getFlights()
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(10);
        log.info("End");
    }

}
