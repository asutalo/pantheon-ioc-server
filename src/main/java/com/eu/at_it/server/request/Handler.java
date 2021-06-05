package com.eu.at_it.server.request;

import com.eu.at_it.server.endpoint.Endpoint;
import com.eu.at_it.server.endpoint.EndpointProcessor;
import com.eu.at_it.server.endpoint.Registry;
import com.eu.at_it.server.request.parsing.ParsingService;
import com.eu.at_it.server.request.validation.Validator;
import com.eu.at_it.server.response.Response;
import com.eu.at_it.server.response.exception.InternalServerErrorException;
import com.eu.at_it.server.response.exception.IocServerException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Handler implements HttpHandler {
    private final Validator validator;
    private final ParsingService parsingService;
    private final Registry registry;
    private final EndpointProcessor endpointProcessor;

    public Handler(Validator validator, ParsingService parsingService, Registry registry, EndpointProcessor endpointProcessor) {
        this.validator = validator;
        this.parsingService = parsingService;
        this.registry = registry;
        this.endpointProcessor = endpointProcessor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String decodedUriString = parsingService.decodeUri(httpExchange);
            validator.validateUri(decodedUriString);
            Endpoint endpoint = registry.getEndpoint(decodedUriString);
            Response response = endpointProcessor.process(endpoint, parsingService, decodedUriString, httpExchange);
            respond(httpExchange, response);
        } catch (IocServerException iocServerException) {
            respond(httpExchange, iocServerException);
        } catch (Exception e) {
            //todo log exception
            respond(httpExchange, new InternalServerErrorException());
        }
    }

    private void respond(HttpExchange httpExchange, Response response) throws IOException {
        httpExchange.getResponseHeaders().putAll(response.getHeaders());

        OutputStream outputStream = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(response.getStatusCode(), response.getMessage().length());
        outputStream.write(response.getMessage().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
