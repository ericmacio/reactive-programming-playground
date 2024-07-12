package com.eric.sec07;

import com.eric.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {

    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel(5)
                .runOn(Schedulers.parallel())
                .map(Lec08Parallel::process1)
                .sequential()
                .map(Lec08Parallel::process2)
                .subscribe(Util.subscriber("sub1"));
        Util.sleepSeconds(10);
        log.info("End");
    }
    private static int process1(int i) {
        log.info("process1");
        Util.sleepSeconds(1);
        return i * 2;
    }

    private static int process2(int i) {
        log.info("process2");
        Util.sleepSeconds(1);
        return i * 10;
    }


}
