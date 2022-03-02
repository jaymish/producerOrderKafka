package com.order.orderkafka.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.repo.OrderRepository;
import com.order.orderkafka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    //List<Weather> list=new LinkedList<>();

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, RestTemplate restTemplate, ObjectMapper objectMapper){
        this.orderRepository = orderRepository;
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
    }

    @Override
    public boolean addWeatherReadings(Orders weather) throws JsonProcessingException {
        //list.add(weather);
        orderRepository.save(weather);
        /*if(weather.getTemperature()>25){
            WeatherAlert weatherAlert=new WeatherAlert("Too Hot", weather);
            String message = objectMapper.writeValueAsString(weatherAlert);
            weatherAlertSns.send("Temperature Alert", message);
//            return restTemplate.postForObject("http://localhost:8081/addReading",weatherAlert,boolean.class);
        }
        if (weather.getWind().getSpeed()>6){
            WeatherAlert weatherAlert=new WeatherAlert("Too Windy", weather);
            String message = objectMapper.writeValueAsString(weatherAlert);
            weatherAlertSns.send("Wind Alert", message);
//            return restTemplate.postForObject("http://localhost:8081/addReading",weatherAlert,boolean.class);
        }*/
        return true;
    }

    @Override
    public List<Orders> getWeatherReadingsSorted() {
        List<Orders> weatherList= (List<Orders>) orderRepository.findAll();
        //weatherList.sort(Comparator.comparing(Weather::getTimestamp));
        return weatherList;
    }
}
