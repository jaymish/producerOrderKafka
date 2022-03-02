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


    private final KafkaTemplate<String, Orders> weatherKafkaTemplate;


    @Value("${kafka.topic.json.name}")
    private String JSON_TOPIC;

    public ProducerService(KafkaTemplate<String, Orders> weatherKafkaTemplate) {

        this.weatherKafkaTemplate = weatherKafkaTemplate;
    }



    public void sendMessageJson(Orders orders){
        log.info(String.format("$$$$ => Consumed Message: %s", orders));

        weatherKafkaTemplate.executeInTransaction(t -> {
            t.send(JSON_TOPIC, orders.getId(), orders);

            return true;
        });
    }

}
