package com.github.rahmnathan.kafka.test.executable;

import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.StreamsBuilderFactoryBean;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
public class KafkaStreamer {
    private final Logger logger = LoggerFactory.getLogger(KafkaStreamer.class.getName());
    private final KStream<String, MyDataObject> myKStream;

    public KafkaStreamer(KStream<String, MyDataObject> myKStream) {
        this.myKStream = myKStream;
    }

    @PostConstruct
    public void streamTest(){
        myKStream.filter((key, value) -> value.getAge() == 23)
                .foreach((key, value) -> logger.info("Key: {} Value: {}", key, value));
    }
}
