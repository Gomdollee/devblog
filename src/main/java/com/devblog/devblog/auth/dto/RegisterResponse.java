package com.devblog.devblog.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RegisterResponse {

    private Long userId;
    private String name;
    private String email;
    private LocalDateTime createdAt;

}
