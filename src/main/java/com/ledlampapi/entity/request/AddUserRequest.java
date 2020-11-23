package com.ledlampapi.entity.request;

import lombok.Data;

@Data
public class AddUserRequest {

    private String email;
    private String password;
    private String name;
    private String surname;
}