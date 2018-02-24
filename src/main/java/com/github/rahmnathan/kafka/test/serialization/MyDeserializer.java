package com.github.rahmnathan.kafka.test.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class MyDeserializer implements Deserializer<MyDataObject> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public MyDataObject deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, MyDataObject.class);
        } catch (IOException e){
            throw new RuntimeException("Deserialization failure", e);
        }
    }

    @Override
    public void close() {

    }
}
