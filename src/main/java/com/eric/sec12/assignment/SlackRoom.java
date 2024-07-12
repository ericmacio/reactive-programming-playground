package com.eric.sec12.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {

    private static final Logger log = LoggerFactory.getLogger(SlackRoom.class);

    private final String name;
    private final Sinks.Many<SlackMessage> sink;
    private final Flux<SlackMessage> flux;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = this.sink.asFlux();
    }

    public void addMember(SlackMember slackMember) {
        log.info("{} joined the room {}", slackMember.getName(), this.name);
        this.subscribeToRoomMessages(slackMember);
        slackMember.setMessageConsumer(msg -> this.postMessages(slackMember.getName(), msg));
    }

    private void subscribeToRoomMessages(SlackMember slackMember) {
        this.flux
                .filter(slackMsg -> !slackMsg.sender().equals(slackMember.getName()))
                .map(slackMsg -> slackMsg.formatForDelivery(slackMember.getName()))
                .subscribe(slackMember::receives);
    }

    private void postMessages(String sender, String message) {
        this.sink.tryEmitNext(new SlackMessage(sender, message));
    }
}
