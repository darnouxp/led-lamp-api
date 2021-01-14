package com.ledlampapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledlampapi.entity.Color;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import org.springframework.web.client.RestTemplate;

@Service
public class LampService {

    ObjectMapper objectMapper = new ObjectMapper();
    String esp32URL = "http://172.20.10.3";
    RestTemplate restTemplate = new RestTemplate();

    public Color changeColor(String changeColorRequest) throws JsonProcessingException {

        Color color = objectMapper.readValue(changeColorRequest, Color.class); //Create color object from parameter

        HttpEntity<Color> request = new HttpEntity<>(color);
        restTemplate.postForObject(esp32URL + "/changeColor", request, String.class); //send color request to esp32
        return color;
    }

    public String getLuminosity() {
        ResponseEntity<String> response = restTemplate.getForEntity(esp32URL + "/getLuminosity", String.class);
        return response.getBody();
    }
}
