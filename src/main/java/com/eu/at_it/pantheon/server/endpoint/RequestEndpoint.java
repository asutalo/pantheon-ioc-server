package com.eu.at_it.pantheon.server.endpoint;

import com.eu.at_it.pantheon.server.endpoints.PantheonEndpoint;
import com.eu.at_it.pantheon.server.request.parsing.Parser;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Main definition of an {@link Endpoint}
 * Describes all supported HTTP verbs and the supported interactions and provides the plumbing for enabling
 * internal endpoint mapping
 */
abstract class RequestEndpoint implements PantheonEndpoint {
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
}
