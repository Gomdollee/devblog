package com.devblog.devblog.auth.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String name;
    private String email;

}
