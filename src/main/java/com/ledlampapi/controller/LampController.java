package com.ledlampapi.controller;

import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.ChangeColorRequest;
import com.ledlampapi.service.LampService;
import com.ledlampapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void changeColor(@RequestBody ChangeColorRequest changeColorRequest){
        ls.changeColor(changeColorRequest);
    }

}
