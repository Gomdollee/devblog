package com.devblog.devblog.auth.dto;

import lombok.Getter;

@Getter
public class RegisterRequest {

    private String name;
    private String email;
    private String password;


}
