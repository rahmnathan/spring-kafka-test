package com.github.rahmnathan.kafka.test.executable;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.ManagedBean;

@ManagedBean
public class KafkaHandler {
    private final Logger logger = LoggerFactory.getLogger(KafkaHandler.class);
    private final KafkaTemplate<String, MyDataObject> template;

    public KafkaHandler(KafkaTemplate<String, MyDataObject> template) {
        this.template = template;
    }

    public void run() {
        template.send("myTopic", new MyDataObject("Nathan", "Joppa asdf", 23));
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) {
        logger.info("Got record from myTopic");
        logger.info(cr.toString());
    }
}
