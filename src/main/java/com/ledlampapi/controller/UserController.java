package com.ledlampapi.controller;

import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddFavColorRequest;
import com.ledlampapi.entity.request.LogInUser;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest addUserRequest){
        return ResponseEntity.ok().body( "{\"email\" : \"" +us.addUser(addUserRequest).getEmail()+ "\"}");
    }

    @PostMapping("/addFavColor")
    public void addFavColor (@RequestBody AddFavColorRequest addFavColorRequest){
        us.addFavColor(addFavColorRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> logInUser(@RequestBody LogInUser liu)
            throws Exception {
        try {
            User user = us.loginUser(liu);
            if(user != null){
                final long id = user.getId();
                return ResponseEntity.ok().body("{\"email\" : \"" + liu.getEmail() + "\"}");
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
