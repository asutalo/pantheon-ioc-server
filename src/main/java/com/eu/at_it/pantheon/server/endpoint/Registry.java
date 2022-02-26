package com.eu.at_it.pantheon.server.endpoint;

import com.eu.at_it.pantheon.server.response.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    private final Map<String, IoCEndpoint> endpoints = new HashMap<>();

    public void registerEndpoint(IoCEndpoint endpoint) {
        String parsedUriDefinition = endpoint.parsedUriDefinition();

        if (!endpoints.containsKey(parsedUriDefinition)) {

            endpoints.put(parsedUriDefinition, endpoint);
        } else {
            throw new IllegalStateException("Endpoint already registered: " + endpoint.uriDefinition());
        }
    }

    public IoCEndpoint getEndpoint(String uriString) {
        return endpoints.values().stream().filter(endpoint -> endpoint.match(uriString)).findFirst().orElseThrow(NotFoundException::new);
    }

    //for tests
    public Map<String, IoCEndpoint> getEndpoints() {
        return endpoints;
    }

    //for tests
    public void clearEndpoints(){
        endpoints.clear();
    }
}
