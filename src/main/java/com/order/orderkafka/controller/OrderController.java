package com.order.orderkafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;
import com.order.orderkafka.service.kafka.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Order")
public class OrderController {

    private ProducerService producerService;



    @Autowired
    public OrderController(ProducerService producerService){
        this.producerService=producerService;
    }


    @PostMapping("/CreateOrder")
    public boolean createOrder(@RequestBody Orders orders) throws JsonProcessingException {
        System.out.println(orders);
        producerService.sendMessageJson(orders);
        return true;
    }

    @PostMapping("/updateorder")
    public boolean updateOrder(@RequestBody Updates updates) throws JsonProcessingException {
        System.out.println(updates);
        producerService.sendMessageUpdate(updates);
        return true;
    }

}
