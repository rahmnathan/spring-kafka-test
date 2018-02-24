package com.github.rahmnathan.kafka.test.serialization;

import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class MySerde implements Serde<MyDataObject> {
    private final MySerializer mySerializer = new MySerializer();
    private final MyDeserializer myDeserializer = new MyDeserializer();

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
}
