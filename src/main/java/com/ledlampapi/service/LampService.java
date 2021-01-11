package com.ledlampapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledlampapi.entity.Color;
import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.ChangeColorRequest;
import com.ledlampapi.repository.LampRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ledlampapi.proxies.ESP32Server;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Service
public class LampService {

    ObjectMapper objectMapper = new ObjectMapper();
    String esp32URL = "http://localhost:8080"; //A MODIFIER
    RestTemplate restTemplate = new RestTemplate();

    public void changeColor(String changeColorRequest) throws JsonProcessingException {

        Color color = objectMapper.readValue(changeColorRequest, Color.class); //Create color object from parameter

        HttpEntity<Color> request = new HttpEntity<>(color);
        restTemplate.postForObject(esp32URL + "/changeColor", request, Color.class); //send color request to esp32
        System.out.println("working");
    }
}
