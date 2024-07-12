package com.eric.test.room;

import com.eric.test.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Room {

    private static final Logger log = LoggerFactory.getLogger(Room.class);
    private final String name;
    private final Sinks.Many<String> sink;
    private final Flux<String> flux;

    public Room(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = this.sink.asFlux();
    }

    public void join(User user) {
        log.info("{} joined the room {}", user.getName(), this.name);
        this.flux.subscribe(user::receiveMsg);
//        this.flux.subscribe(Util.subscriber(user.getName()));
    }

    public void sendMsg(String msg) {
        this.sink.tryEmitNext(msg);
    }
}
