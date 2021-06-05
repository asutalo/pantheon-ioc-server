package com.eu.at_it.server.endpoint;

import com.eu.at_it.server.request.parsing.Parser;
import com.eu.at_it.server.response.Response;
import com.eu.at_it.server.response.exception.NotImplementedException;
import com.sun.net.httpserver.Headers;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Main definition of an {@link Endpoint}
 * Describes all supported HTTP verbs and the supported interactions and provides the plumbing for enabling
 * internal endpoint mapping
 */
abstract class RequestEndpoint {
    private final String parsedUriDefinition;
    private final List<Parser> uriParsers;
    private final Pattern uriMatchingPattern;

    public RequestEndpoint(String parsedUriDefinition, List<Parser> uriParsers) {
        this.uriMatchingPattern = Pattern.compile(parsedUriDefinition);
        this.parsedUriDefinition = parsedUriDefinition;
        this.uriParsers = uriParsers;
    }

    public List<Parser> uriParsers() {
        return uriParsers;
    }

    boolean match(String uriString) {
        return uriMatchingPattern.matcher(uriString).matches();
    }

    String parsedUriDefinition() {
        return parsedUriDefinition;
    }

    public Response head(Map<String, Object> uriParams, Map<String, Object> requestBody, Headers requestHeaders) {
        throw new NotImplementedException();
    }

    public Response get(Map<String, Object> uriParams, Map<String, Object> requestBody, Headers requestHeaders) {
        throw new NotImplementedException();
    }

    public Response put(Map<String, Object> uriParams, Map<String, Object> requestBody, Headers requestHeaders) {
        throw new NotImplementedException();
    }

    public Response post(Map<String, Object> uriParams, Map<String, Object> requestBody, Headers requestHeaders) {
        throw new NotImplementedException();
    }

    public Response patch(Map<String, Object> uriParams, Map<String, Object> requestBody, Headers requestHeaders) {
        throw new NotImplementedException();
    }

    public Response delete(Map<String, Object> uriParams, Map<String, Object> requestBody, Headers requestHeaders) {
        throw new NotImplementedException();
    }
}
