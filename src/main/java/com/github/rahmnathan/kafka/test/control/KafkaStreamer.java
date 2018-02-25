package com.github.rahmnathan.kafka.test.control;

import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
public class KafkaStreamer {
    private final Logger logger = LoggerFactory.getLogger(KafkaStreamer.class.getName());
    private final KStream<String, MyDataObject> myKStream;

    public KafkaStreamer(KStream<String, MyDataObject> myKStream) {
        this.myKStream = myKStream;
    }

    @PostConstruct
    public Map<String, MyDataObject> getEventsByAge(){
        Map<String, MyDataObject> dataObjectMap = new HashMap<>();

        myKStream.filter((key, value) -> value.getAge() == 23)
                .foreach((key, value) -> {
                    logger.info("Key: {} Value: {}", key, value);
                    dataObjectMap.put(key, value);
                });

        return dataObjectMap;
    }
}
