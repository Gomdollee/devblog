package com.devblog.devblog.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String token;
    private String tokenType;
    private Long userId;
    private String name;
    private String email;

}
