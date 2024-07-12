package com.eric.sec02;

import com.eric.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    public static void main(String[] args) {

        getUserName(1)
                .subscribe(Util.subscriber("sub1"));
        getUserName(2)
                .subscribe(Util.subscriber("sub2"));
        getUserName(3)
                .subscribe(Util.subscriber("sub3"));
    }

    public static Mono<String> getUserName(int userId) {
        return switch(userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new IllegalArgumentException("Invalid userId: " + userId));
        };
    }
}
