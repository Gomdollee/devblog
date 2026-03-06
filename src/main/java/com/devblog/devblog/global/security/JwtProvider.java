package com.devblog.devblog.global.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private final byte[] keyBytes; // JWT 라이브러리가 byte[] 기반으로 서명
    private final long expirationMillis; // 토큰 만료시간

    public JwtProvider(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-seconds}") long expirationMillis
    ) {
        this.keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.expirationMillis = expirationMillis * 1000; // 1시간 유지

    }

    public String generateToken(Long userId, String email) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email", email)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(Keys.hmacShaKeyFor(keyBytes))
                .compact();
    }
}
