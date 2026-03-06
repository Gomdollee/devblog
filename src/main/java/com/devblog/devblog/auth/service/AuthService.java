package com.devblog.devblog.auth.service;

import com.devblog.devblog.auth.dto.LoginRequest;
import com.devblog.devblog.auth.dto.LoginResponse;
import com.devblog.devblog.auth.dto.RegisterRequest;
import com.devblog.devblog.auth.dto.RegisterResponse;
import com.devblog.devblog.global.exception.DevBlogException;
import com.devblog.devblog.global.exception.ErrorCode;
import com.devblog.devblog.global.security.JwtProvider;
import com.devblog.devblog.user.entity.User;
import com.devblog.devblog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 회원가입시 적합성 검사
     */
    public RegisterResponse register(RegisterRequest request) {

        /**
         * 요건 : 이미 사용 중인 이메일 -> BAD_REQUEST
         */
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DevBlogException(
                    ErrorCode.BAD_REQUEST,
                    "이미 사용 중인 이메일입니다."
            );
        }

        /**
         * 요건 : 비밀번호 8자 미만 -> VALIDATION_ERROR
         */
        if (request.getPassword() == null || request.getPassword().length() < 8 ) {
            throw new DevBlogException(
                    ErrorCode.BAD_REQUEST,
                    "비밀번호 8자 이상이어야 합니다."
            );
        }

        String encodePassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getName(),
                request.getEmail(),
                encodePassword
        );

        userRepository.save(user);

        return RegisterResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    /**
     * 로그인
     */
    public LoginResponse login(LoginRequest request) {

        /**
         * 요건 : 이메일/비밀번호 불일치 -> BAD_REQUEST
         */
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new DevBlogException(
                        ErrorCode.BAD_REQUEST,
                        "이메일/비밀번호 불일치"
                ));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new DevBlogException(
                    ErrorCode.BAD_REQUEST,
                    "이메일/비밀번호 불일치"
            );
        }

        String token = jwtProvider.generateToken(user.getId(), user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
