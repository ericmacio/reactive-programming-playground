package com.eric.sec03;

import com.eric.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec11FluxMono {

    public static void main(String[] args) {

        monoToFlux(1, "monoToFluxSub1");
        monoToFlux(2, "monoToFluxSub2");
        monoToFlux(3, "monoToFluxSub3");

        Flux<Integer> flux = Flux.range(1, 10);
        flux.next()
                .subscribe(Util.subscriber("fluxToMonoSub1"));
        Mono.from(flux)
                .subscribe(Util.subscriber("fluxToMonoSub2"));

    }

    private static void monoToFlux(int userId, String subName) {
        Mono<String> mono = getUserName(userId);
        save(Flux.from(mono), subName);

    }

    private static Mono<String> getUserName(int userId) {
        return switch(userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Bad user id: " + userId));
        };
    }

    private static void save(Flux<String> flux, String subName) {
        flux.subscribe(Util.subscriber(subName));
    }
}
