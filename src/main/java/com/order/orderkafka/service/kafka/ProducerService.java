package com.order.orderkafka.service.kafka;



import com.order.orderkafka.model.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Orders> weatherKafkaTemplate;

    @Value("${kafka.topic.string-demo.name}")
    private String STRING_TOPIC;

    @Value("${kafka.topic.json.name}")
    private String JSON_TOPIC;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Orders> weatherKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.weatherKafkaTemplate = weatherKafkaTemplate;
    }

    public void sendMessage(String message){
        log.info(String.format("$$$$ => Consumed Message: %s", message));
        List<String> keyList = Arrays.asList("KEY1","KEY2","KEY3","KEY4","KEY5");

        kafkaTemplate.executeInTransaction(t -> {
             t.send(STRING_TOPIC, keyList.get(new Random().nextInt(keyList.size())), message);

            return true;
        });
    }

    public void sendMessageJson(Orders orders){
        log.info(String.format("$$$$ => Consumed Message: %s", orders));

        weatherKafkaTemplate.executeInTransaction(t -> {
            t.send(JSON_TOPIC, orders.getId(), orders);

            return true;
        });
    }

}
