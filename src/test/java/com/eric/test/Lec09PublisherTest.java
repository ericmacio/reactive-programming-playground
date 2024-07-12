package com.eric.test;

import com.eric.common.Util;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherTest.class);

   private UnaryOperator<Flux<String>> processor() {
       return flux -> flux
               .filter(s -> s.length() > 1)
               .map(String::toUpperCase)
               .map(s -> s + ":" + s.length());
   }

   @Test
   public void publisherTest1() {
       TestPublisher<String> publisher = TestPublisher.create();
       Flux<String> flux = publisher.flux();
//       flux.subscribe(Util.subscriber("sub1"));
//       publisher.next("a", "b", "c");
//       publisher.complete();

       StepVerifier.create(flux.transform(processor()))
               .then(() -> publisher.emit("hello", "bonjour"))
               .expectNext("HELLO:5")
               .expectNext("BONJOUR:7")
               .expectComplete()
               .verify();
   }

    @Test
    public void publisherTest2() {
        TestPublisher<String> publisher = TestPublisher.create();
        Flux<String> flux = publisher.flux();
        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();
    }
}
