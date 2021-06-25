package com.eu.at_it.server.endpoint;

import com.eu.at_it.server.response.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    private final Map<String, Endpoint> endpoints = new HashMap<>();

    public void registerEndpoint(Endpoint endpoint) {
        String parsedUriDefinition = endpoint.parsedUriDefinition();

        if (!endpoints.containsKey(parsedUriDefinition)) {

            endpoints.put(parsedUriDefinition, endpoint);
        } else {
            throw new IllegalStateException("Endpoint already registered: " + endpoint.uriDefinition());
        }
    }

    public Endpoint getEndpoint(String uriString) {
        return endpoints.values().stream().filter(endpoint -> endpoint.match(uriString)).findFirst().orElseThrow(NotFoundException::new);
    }

    //for tests
    public Map<String, Endpoint> getEndpoints() {
        return endpoints;
    }

    //for tests
    public void clearEndpoints(){
        endpoints.clear();
    }
}
