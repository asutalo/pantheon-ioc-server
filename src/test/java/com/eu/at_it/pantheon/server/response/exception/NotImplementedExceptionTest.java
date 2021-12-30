package com.eu.at_it.pantheon.server.response.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotImplementedExceptionTest {
    @Test
    void shouldHaveCorrectStatusAndMessage() {
        int expectedStatus = 501;
        String expectedMessage = "Not Implemented";
        NotImplementedException notImplementedException = new NotImplementedException();

        Assertions.assertEquals(expectedStatus, notImplementedException.getStatusCode());
        Assertions.assertEquals(expectedMessage, notImplementedException.getMessage());
    }

}