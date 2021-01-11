package com.ledlampapi.entity.request;


import lombok.Data;

@Data
public class LogInUser {

    private String email;
    private String password;

    public LogInUser(String email, String password){
        this.email=email;
        this.password=password;
    }

}
