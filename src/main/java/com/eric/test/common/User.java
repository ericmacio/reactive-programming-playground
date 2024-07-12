package com.eric.test.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {

    private static final Logger log = LoggerFactory.getLogger(User.class);
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receiveMsg(String msg) {
        log.info("{} received msg: {}", this.name, msg);
    }
}
