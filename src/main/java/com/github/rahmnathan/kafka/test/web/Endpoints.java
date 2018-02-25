package com.github.rahmnathan.kafka.test.web;

import com.github.rahmnathan.kafka.test.control.KafkaHandler;
import com.github.rahmnathan.kafka.test.control.KafkaStreamer;
import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api/v1/kafka/event")
public class Endpoints {
    private final Logger logger = LoggerFactory.getLogger(Endpoints.class.getName());
    private final KafkaStreamer kafkaStreamer;
    private final KafkaHandler kafkaHandler;

    public Endpoints(KafkaHandler kafkaHandler, KafkaStreamer kafkaStreamer) {
        this.kafkaHandler = kafkaHandler;
        this.kafkaStreamer = kafkaStreamer;
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    public void putEvent(@RequestBody MyDataObject myDataObject){
        logger.info("Received request to store event: {}", myDataObject);
        kafkaHandler.sendEvent(myDataObject);
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.GET)
    public ResponseEntity getEvent(@RequestParam("age") String age){
        logger.info("Received request to get events with age: {}", age);
        return ResponseEntity.ok(kafkaStreamer.getEventsByAge());
    }
}
