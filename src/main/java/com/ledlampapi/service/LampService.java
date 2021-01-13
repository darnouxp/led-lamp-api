package com.ledlampapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledlampapi.entity.Color;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;


import org.springframework.web.client.RestTemplate;

@Service
public class LampService {

    ObjectMapper objectMapper = new ObjectMapper();
    String esp32URL = "http://localhost:8080"; //A MODIFIER
    RestTemplate restTemplate = new RestTemplate();

    public Color changeColor(String changeColorRequest) throws JsonProcessingException {

        Color color = objectMapper.readValue(changeColorRequest, Color.class); //Create color object from parameter

        HttpEntity<Color> request = new HttpEntity<>(color);
        restTemplate.postForObject(esp32URL + "/changeColor", request, Color.class); //send color request to esp32
        return color;
    }
}
