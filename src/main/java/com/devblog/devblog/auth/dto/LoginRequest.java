package com.devblog.devblog.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String email;
    private String password;

}
