package com.ledlampapi.controller;

import com.ledlampapi.entity.Color;
import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddFavColorRequest;
import com.ledlampapi.entity.request.Email;
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
    public ResponseEntity<Object> addFavColor (@RequestBody AddFavColorRequest addFavColorRequest){
        System.out.println("addFavColor");
        Color color = us.addFavColor(addFavColorRequest);
        return ResponseEntity.ok().body("{\"red\" : \"" + color.getRed() + "\",\"green\" : \""+color.getGreen()+ "\",\"blue\" : \""+color.getBlue()+"\"}");
    }

    @PostMapping("/getFavColor")
    public ResponseEntity<Object> getFavColor (@RequestBody Email email)throws Exception{
            try {
                System.out.println("getFavColor");
                Color color = us.getFavColor(email);
                if(color != null){
                    return ResponseEntity.ok().body("{\"red\" : \"" + color.getRed() + "\",\"green\" : \""+ color.getGreen()+ "\",\"blue\" : \""+ color.getBlue()+ "\"}");
                }else{
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }

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
