package com.github.rahmnathan.kafka.test.executable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.ManagedBean;

@ManagedBean
public class KafkaHandler {
    private final Logger logger = LoggerFactory.getLogger(KafkaHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, String> template;

    public KafkaHandler(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void run(){
        try {
            MyDataObject myDataObject = new MyDataObject("Nathan", "Joppa asdf", 23);
            template.send("myTopic", objectMapper.writeValueAsString(myDataObject));
        }catch (JsonProcessingException e){
            logger.error("JSON FAILURE", e);
        }
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) {
        logger.info("Got record from myTopic");
        logger.info(cr.toString());
    }
}
