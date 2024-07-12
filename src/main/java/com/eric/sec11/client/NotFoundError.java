package com.eric.sec11.client;

public class NotFoundError extends RuntimeException {

    public NotFoundError() {
        super("Not Found");
    }

}
