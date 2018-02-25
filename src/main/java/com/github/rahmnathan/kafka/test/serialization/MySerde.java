package com.github.rahmnathan.kafka.test.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class MySerde implements Serde<MyDataObject> {
    private static  final ObjectMapper objectMapper = new ObjectMapper();
    private  final MyDeserializer myDeserializer = new MyDeserializer();
    private final MySerializer mySerializer = new MySerializer();

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        myDeserializer.configure(map, b);
        mySerializer.configure(map, b);
    }

    @Override
    public void close() {
        myDeserializer.close();
        mySerializer.close();
    }

    @Override
    public Serializer<MyDataObject> serializer() {
        return mySerializer;
    }

    @Override
    public Deserializer<MyDataObject> deserializer() {
        return myDeserializer;
    }

    public static class MyDeserializer implements Deserializer<MyDataObject> {

        public MyDeserializer() {
        }

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

    public static class MySerializer implements Serializer<MyDataObject> {
        private boolean isKey;

        public MySerializer() {

        }

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
}
