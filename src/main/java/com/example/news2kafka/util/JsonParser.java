package com.example.news2kafka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class JsonParser {
    private final ObjectMapper objectMapper;

    public <T> T readFromJson(String payload, Class<T> valueType) {
        try {
            return objectMapper.readValue(payload, valueType);
        } catch (Exception e) {
            log.error("An error has occurred while reading from JSON: payload={}", payload, e);
        }

        return null;
    }

    public <T> String writeToJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("An error has occurred while converting the object to JSON, object={}", object, e);
        }

        return null;
    }
}
