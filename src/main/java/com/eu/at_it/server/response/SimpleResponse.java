package com.eu.at_it.server.response;

/**
 * A utility class, most basic version of a Response
 * */
public class SimpleResponse implements Response {
    private final int statusCode;
    private final String message;

    public SimpleResponse(int statusCode, String message) {
        this.statusCode = statusCode;

        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}