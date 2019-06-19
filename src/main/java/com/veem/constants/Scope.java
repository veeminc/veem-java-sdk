package com.veem.constants;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 */
@AllArgsConstructor
@Getter
public enum Scope
{
    ALL("all");

    private String stringValue;

    public static class Converter extends JsonDeserializer<Scope>
    {

        @Override
        public Scope deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            final String scopeValue = p.getText();

            return Arrays.stream(Scope.values())
                    .filter(scope ->  scope.getStringValue().equals(scopeValue))
                    .findFirst().orElse(null);

        }
    }
}
