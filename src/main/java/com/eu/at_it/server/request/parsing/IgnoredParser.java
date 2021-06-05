package com.eu.at_it.server.request.parsing;

import java.util.Map;

class IgnoredParser implements Parser {
    @Override
    public void accept(Map<String, Object> stringObjectMap, String o) {
        //doNothing
    }
}
