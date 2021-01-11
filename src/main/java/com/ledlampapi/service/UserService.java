package com.ledlampapi.service;

import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddFavColorRequest;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(AddUserRequest addUserRequest){
        User user = new User();
        user.setEmail(addUserRequest.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        userRepository.save(user);
    }

    public void addFavColor(AddFavColorRequest addFavColorRequest) {
        User user = userRepository.searchEmail(addFavColorRequest.getEmail());
        user.setRed(addFavColorRequest.getRed());
        user.setGreen(addFavColorRequest.getGreen());
        user.setBlue(addFavColorRequest.getBlue());
        userRepository.save(user);
    }
}
