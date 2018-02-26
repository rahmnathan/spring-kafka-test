package com.github.rahmnathan.kafka.test.control;

import com.github.rahmnathan.kafka.test.data.MyDataObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
public class KafkaHandler {
    private final Logger logger = LoggerFactory.getLogger(KafkaHandler.class);
    private final Map<String, List<MyDataObject>> dataObjectMap = new HashMap<>();
    private final KafkaTemplate<String, MyDataObject> template;

    public KafkaHandler(KafkaTemplate<String, MyDataObject> template) {
        this.template = template;
    }

    public void sendEvent(MyDataObject myDataObject) {
        template.send("myTopic8", myDataObject.getId(), myDataObject);
    }

    public List<MyDataObject> getEventList(String key) {
        return dataObjectMap.get(key);
    }

    @KafkaListener(topics = "myTopic8")
    public void listen(ConsumerRecord<String, MyDataObject> cr) {
        logger.info(cr.toString());
        String key = cr.key();
        MyDataObject myDataObject = cr.value();
        myDataObject.setTimestamp(cr.timestamp());
        myDataObject.setOffset(cr.offset());

        String realKey = key == null ? "StubKey" : key;

        if (dataObjectMap.containsKey(realKey)) {
            List<MyDataObject> existingValue = dataObjectMap.get(realKey);
            existingValue.add(myDataObject);
        } else {
            List<MyDataObject> newValue = new ArrayList<>();
            newValue.add(myDataObject);

            dataObjectMap.put(realKey, newValue);
        }
    }
}
