package com.order.orderkafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.service.OrderService;
import com.order.orderkafka.service.kafka.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/weather")
public class OrderController {

    private OrderService orderService;
    private ProducerService producerService;



    @Autowired
    public OrderController(OrderService orderService, ProducerService producerService){
        this.orderService = orderService;
        this.producerService=producerService;
    }


    @PostMapping("/addReading")
    public boolean addWeatherReading(@RequestBody Orders orders) throws JsonProcessingException {
        System.out.println(orders);
        //weatherService.addWeatherReadings(weather);
        producerService.sendMessageJson(orders);
        return true;
    }

}
