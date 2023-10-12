package com.example.kafkaConsumer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public <T> T jsonToObject(String jsonString, Class<T> valueType) {
        try {
            T object = objectMapper.readValue(jsonString, valueType);
            return object;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String objectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
