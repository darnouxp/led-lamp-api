package com.ledlampapi.entity.request;

import lombok.Data;

@Data
public class AddFavColorRequest {

    private String email;
    private int red;
    private int green;
    private int blue;
}
