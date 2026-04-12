package com.cinnamonshake.storefront.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String email;
    private String password;
}