package com.eu.at_it.server.endpoint;

import com.eu.at_it.server.response.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistryTest {
    @Test
    void isSingleton() {
        assertEquals(Registry.getInstance(), Registry.getInstance());
    }

    @Test
    void registerEndpoint_shouldAddNewEndpoint() {
        Registry registry = Registry.getInstance();
        Map<String, Endpoint> startingEndpoints = new HashMap<>(registry.getEndpoints());

        registry.registerEndpoint(new TestEndpoint(""));

        Assertions.assertNotEquals(startingEndpoints, registry.getEndpoints());
    }

    @Test
    void registerEndpoint_shouldThrowExceptionWhenEndpointAlreadyRegistered() {
        Registry registry = Registry.getInstance();

        Endpoint duplicate = new TestEndpoint("duplicate");

        if (!registry.getEndpoints().containsKey(duplicate.parsedUriDefinition())) {
            registry.registerEndpoint(duplicate);
        }

        Assertions.assertThrows(IllegalStateException.class, () -> registry.registerEndpoint(duplicate));
    }

    @Test
    void getEndpoint_shouldReturnExistingEndpoint() {
        Registry registry = Registry.getInstance();

        String existingUriDefinition = "existing";
        Endpoint existing = new TestEndpoint(existingUriDefinition);

        if (!registry.getEndpoints().containsKey(existing.parsedUriDefinition())) {
            registry.registerEndpoint(existing);
        }

        assertEquals(existing, registry.getEndpoint(existingUriDefinition));
    }

    @Test
    void getEndpoint_shouldThrowNotFoundExceptionWhenNoEndpointDefined() {
        Registry registry = Registry.getInstance();
        String notExisting = "notExisting";
        Assertions.assertThrows(NotFoundException.class, () -> registry.getEndpoint(notExisting));
    }

    @Test
    void clearEndpoints(){
        Registry registry = Registry.getInstance();
        registry.registerEndpoint(new TestEndpoint("some"));
        registry.clearEndpoints();

        Assertions.assertTrue(registry.getEndpoints().isEmpty());
    }

    static class TestEndpoint extends Endpoint {
        public TestEndpoint(String uriDefinition) {
            super(uriDefinition);
        }
    }
}