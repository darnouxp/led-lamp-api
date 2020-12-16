package com.ledlampapi.service;

import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.ChangeColorRequest;
import com.ledlampapi.repository.LampRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name="lamp", url = "http://172.20.10.2:COM6")
public class LampService {

    private LampRepository lr;

    public void changeColor(ChangeColorRequest changeColorRequest){

    }
}
