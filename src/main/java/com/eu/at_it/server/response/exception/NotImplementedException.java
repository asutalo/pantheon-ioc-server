package com.eu.at_it.server.response.exception;

public class NotImplementedException extends IocServerException {
    public static final Integer NOT_IMPLEMENTED_ERROR_STATUS = 501;
    public static final String NOT_IMPLEMENTED_ERROR_MESSAGE = "Not Implemented";

    public NotImplementedException() {
        super(NOT_IMPLEMENTED_ERROR_STATUS, NOT_IMPLEMENTED_ERROR_MESSAGE);
    }
}
