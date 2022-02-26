package com.eu.at_it.pantheon.server.endpoint;

import com.eu.at_it.pantheon.server.endpoints.PantheonEndpoint;
import com.eu.at_it.pantheon.server.request.parsing.Parser;

import java.util.List;

public interface IoCEndpoint extends PantheonEndpoint {

    List<Parser> uriParsers();

    boolean match(String uriString);

    String parsedUriDefinition();

    String uriDefinition();

}
