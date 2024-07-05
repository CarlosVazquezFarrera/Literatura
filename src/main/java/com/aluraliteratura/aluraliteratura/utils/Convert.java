package com.aluraliteratura.aluraliteratura.utils;

import com.aluraliteratura.aluraliteratura.interfaces.IConvert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Convert implements IConvert {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T convert(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
