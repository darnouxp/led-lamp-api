package com.ledlampapi.entity.request;

import lombok.Data;

@Data
public class ChangeColorRequest {

    private int red;
    private int green;
    private int blue;
}