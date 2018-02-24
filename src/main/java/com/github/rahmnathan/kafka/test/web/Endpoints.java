package com.github.rahmnathan.kafka.test.web;

import com.github.rahmnathan.kafka.test.control.KafkaHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoints {
    private final Logger logger = LoggerFactory.getLogger(Endpoints.class.getName());
    private final KafkaHandler kafkaHandler;

    public Endpoints(KafkaHandler kafkaHandler) {
        this.kafkaHandler = kafkaHandler;
    }

    @RequestMapping("/fire")
    public void run(){
        logger.info("FIRING ROUTES");
        kafkaHandler.run();
        logger.info("ROUTES FIRED");
    }
}
