package com.ledlampapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ledlampapi.entity.Color;
import com.ledlampapi.service.LampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lamp")
public class LampController {

    private LampService ls;

    @Autowired
    public LampController(LampService lampService){this.ls = lampService;}

    @PostMapping("/color")
    public ResponseEntity<String> changeColor(@RequestBody String changeColorRequest) throws JsonProcessingException {
        Color color = ls.changeColor(changeColorRequest);
        return ResponseEntity.ok().body("{\"red\" : \"" + color.getRed() + "\",\"green\" : \""+color.getGreen()+ "\",\"blue\" : \""+color.getBlue()+"\"}");
    }

}
