package com.ledlampapi.controller;

import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.ChangeColorRequest;
import com.ledlampapi.service.LampService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lamp")
public class LampController {

    private LampService ls;

    @PostMapping("/color")
    public void changeColor(@RequestBody ChangeColorRequest changeColorRequest){
        ls.changeColor(changeColorRequest);
    }

}
