package com.devblog.devblog.auth.controller;

import com.devblog.devblog.auth.dto.RegisterRequest;
import com.devblog.devblog.auth.dto.RegisterResponse;
import com.devblog.devblog.auth.service.AuthService;
import com.devblog.devblog.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(
            @RequestBody RegisterRequest request
            ) {
        return ApiResponse.success(authService.register(request));
    }
}
