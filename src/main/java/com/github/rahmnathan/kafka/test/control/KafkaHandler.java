package com.github.rahmnathan.kafka.test.control;

import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.ManagedBean;

@ManagedBean
public class KafkaHandler {
    private final Logger logger = LoggerFactory.getLogger(KafkaHandler.class);
    private final KafkaTemplate<String, MyDataObject> template;

    public KafkaHandler(KafkaTemplate<String, MyDataObject> template) {
        this.template = template;
    }

//    @PostConstruct
    public void run() {
        MyDataObject myDataObject = new MyDataObject("Nathan", "Joppa asdf", 23);
        template.send("myTopic2", "myTestKey", myDataObject);
    }

//    @KafkaListener(topics = "myTopic2")
    public void listen(ConsumerRecord<?, ?> cr) {
        logger.info(cr.toString());
    }
}
