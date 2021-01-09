package com.ledlampapi.controller;

import com.ledlampapi.entity.User;
import com.ledlampapi.repository.UserRepository;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService userService){this.us = userService;}

    @GetMapping("/list")
    public List<User> getUsers(){
        return us.getUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody AddUserRequest addUserRequest){
        us.addUser(addUserRequest);
    }
}
