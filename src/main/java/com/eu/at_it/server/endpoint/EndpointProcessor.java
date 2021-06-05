package com.eu.at_it.server.endpoint;

import com.eu.at_it.server.request.parsing.ParsingService;
import com.eu.at_it.server.response.Response;
import com.eu.at_it.server.response.exception.NotFoundException;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

/**
 * Obtains URI params, and the body, then identifies correct action to execute on the endpoint and runs it
 */
public class EndpointProcessor {

    public Response process(Endpoint endpoint, ParsingService parsingService, String decodedUriString, HttpExchange httpExchange) throws IOException {
        Map<String, Object> uriParams = parsingService.parseRequestUri(
                decodedUriString, endpoint.uriParsers()
        );

        Map<String, Object> requestBody = parsingService.parseRequestBody(httpExchange);

        return switch (httpExchange.getRequestMethod().toUpperCase()) {
            case "HEAD" -> endpoint.head(uriParams, requestBody, httpExchange.getRequestHeaders());
            case "GET" -> endpoint.get(uriParams, requestBody, httpExchange.getRequestHeaders());
            case "POST" -> endpoint.post(uriParams, requestBody, httpExchange.getRequestHeaders());
            case "PUT" -> endpoint.put(uriParams, requestBody, httpExchange.getRequestHeaders());
            case "DELETE" -> endpoint.delete(uriParams, requestBody, httpExchange.getRequestHeaders());
            case "PATCH" -> endpoint.patch(uriParams, requestBody, httpExchange.getRequestHeaders());
            default -> throw new NotFoundException();
        };
    }
}
