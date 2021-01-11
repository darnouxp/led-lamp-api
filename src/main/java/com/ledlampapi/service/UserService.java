package com.ledlampapi.service;

import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.AddFavColorRequest;
import com.ledlampapi.entity.request.AddUserRequest;
import com.ledlampapi.entity.request.LogInUser;
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

    public User addUser(AddUserRequest addUserRequest){
        User user = new User();
        user.setEmail(addUserRequest.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        return userRepository.save(user);
    }

    public User loginUser(LogInUser addUserRequest){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(addUserRequest.getPassword());

        List<User> users = userRepository.findByEmail(addUserRequest.getEmail());
        if (users.isEmpty()){
            return null;
        }
        if (passwordEncoder.matches(addUserRequest.getPassword(), users.get(0).getPassword())){
            return users.get(0);
        }
        return null;
    }

    public void addFavColor(AddFavColorRequest addFavColorRequest) {
        User user = userRepository.searchEmail(addFavColorRequest.getEmail());
        user.setRed(addFavColorRequest.getRed());
        user.setGreen(addFavColorRequest.getGreen());
        user.setBlue(addFavColorRequest.getBlue());
        userRepository.save(user);
    }

    public User getUserFromEmail(String email){
        List<User> users =  userRepository.findByEmail(email);
        return users.isEmpty() ? null : users.get(0);
    }
}
