package com.eric.sec12.assignment;

import com.eric.common.Util;

public class TestAssignment {

    public static void main(String[] args) {

        SlackRoom room = new SlackRoom("reactor");
        SlackMember sam = new SlackMember("sam");
        SlackMember jack = new SlackMember("jack");
        SlackMember mike = new SlackMember("mike");

        room.addMember(sam);
        room.addMember(jack);

        sam.says("Hi all ..");
        Util.sleepSeconds(2);

        jack.says("Hey!");
        sam.says("I simply wanted to say Hi..");
        Util.sleepSeconds(2);

        room.addMember(mike);
        mike.says("Hey guys, how are you today ?");

    }
}
