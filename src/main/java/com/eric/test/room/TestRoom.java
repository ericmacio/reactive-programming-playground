package com.eric.test.room;

import com.eric.test.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestRoom {

    private static final Logger log = LoggerFactory.getLogger(TestRoom.class);

    public static void main(String[] args) {

        Room confRoom = new Room("conference");
        User user1 = new User("user1");
        confRoom.join(user1);
        User user2 = new User("user2");
        confRoom.join(user2);
        confRoom.sendMsg("Hello every one");
        confRoom.sendMsg("How are you today ?");
        User user3 = new User("user3");
        confRoom.join(user3);
        confRoom.sendMsg("Bonjour la France !");
    }


}
