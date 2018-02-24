package com.github.rahmnathan.kafka.test.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class MySerializer implements Serializer<MyDataObject> {
    private ObjectMapper objectMapper = new ObjectMapper();
    private boolean isKey;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        this.isKey = isKey;
    }

    @Override
    public byte[] serialize(String topic, MyDataObject message) {
        if (message == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(message);
        } catch (IOException | RuntimeException e) {
            throw new SerializationException("Error serializing value", e);
        }
    }

    @Override
    public void close() {

    }
}