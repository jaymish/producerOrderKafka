package com.order.orderkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;

import java.util.List;

public interface OrderService {
    boolean addWeatherReadings(Orders orders) throws JsonProcessingException;
    List<Orders> getWeatherReadingsSorted();
}
