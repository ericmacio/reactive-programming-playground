package com.eric.test;

import com.eric.common.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

public class Lec05AssertNextTest {

    private static final Logger log = LoggerFactory.getLogger(Lec05AssertNextTest.class);

    //Method from service class
    record Book(int id, String author, String title) {}
    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.faker().book().author(), Util.faker().book().title()));
    }

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(b -> Assertions.assertEquals(1, b.id()))
                .thenConsumeWhile(b -> Objects.nonNull(b.title()))
                .expectComplete()
                .verify();
    }

    @Test
    public void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }

}
