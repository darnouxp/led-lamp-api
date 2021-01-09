package com.ledlampapi.service;

import com.ledlampapi.entity.Color;
import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.ChangeColorRequest;
import com.ledlampapi.repository.LampRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class LampService {

    private LampRepository lr;

    public void changeColor(ChangeColorRequest changeColorRequest){
        Color color = new Color();
        color.setRed(changeColorRequest.getRed());
        color.setRed(changeColorRequest.getRed());
        color.setRed(changeColorRequest.getRed());
        lr.save(color);
    }
}
