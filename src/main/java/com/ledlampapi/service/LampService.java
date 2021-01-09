package com.ledlampapi.service;

import com.ledlampapi.entity.Color;
import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.ChangeColorRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class LampService {

    public void changeColor(ChangeColorRequest changeColorRequest){
        Color color = new Color();
        color.setRed(changeColorRequest.getRed());
        color.setRed(changeColorRequest.getRed());
        color.setRed(changeColorRequest.getRed());
        System.out.println("working");

    }
}
