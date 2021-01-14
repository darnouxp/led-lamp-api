package com.ledlampapi.entity.request;

import lombok.Data;

@Data
public class UpdateUser {

    private String email;
    private String firstname;
    private String lastname;
    private Integer age;
    private String sex;
}
