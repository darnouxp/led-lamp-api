package com.ledlampapi.service;

import com.ledlampapi.entity.Color;
import com.ledlampapi.entity.User;
import com.ledlampapi.entity.request.*;
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
        user.setFirstname(addUserRequest.getFirstname());
        user.setLastname(addUserRequest.getLastname());
        user.setAge(addUserRequest.getAge());
        user.setSex(addUserRequest.getSex());
        return userRepository.save(user);
    }

    public User updateUser(UpdateUser updateUser){
        User user = userRepository.searchEmail(updateUser.getEmail());
        user.setSex(updateUser.getSex());
        user.setLastname(updateUser.getLastname());
        user.setFirstname(updateUser.getFirstname());
        user.setAge(updateUser.getAge());
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

    public Color addFavColor(AddFavColorRequest addFavColorRequest) {
        User user = userRepository.searchEmail(addFavColorRequest.getEmail());
        user.setRed(addFavColorRequest.getRed());
        user.setGreen(addFavColorRequest.getGreen());
        user.setBlue(addFavColorRequest.getBlue());
        userRepository.save(user);
        Color color = new Color();
        color.setRed(user.getRed());
        color.setBlue(user.getBlue());
        color.setGreen(user.getGreen());
        return color;
    }

    public User getUserFromEmail(String email){
        List<User> users =  userRepository.findByEmail(email);
        return users.isEmpty() ? null : users.get(0);
    }

    public Color getFavColor(Email email) {
        List<User> users =  userRepository.findByEmail(email.getEmail());
        User user =  users.isEmpty() ? null : users.get(0);
        Color color = new Color();
        color.setRed(user.getRed());
        color.setBlue(user.getBlue());
        color.setGreen(user.getGreen());
        System.out.println(color.getBlue());
        System.out.println(color.getRed());
        System.out.println(color.getGreen());
        return color;
    }
}
