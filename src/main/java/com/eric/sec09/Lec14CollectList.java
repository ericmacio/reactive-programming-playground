package com.eric.sec09;

import com.eric.common.Util;
import com.eric.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec14CollectList {

    private static final Logger log = LoggerFactory.getLogger(Lec14CollectList.class);

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();

        Flux.range(1, 10)
                .subscribe(Util.subscriber("sub1"));

        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber("sub2"));
    }
}
