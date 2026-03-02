package com.devblog.devblog.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("BAD_REQUEST", "잘못된 요청입니다.");

    private final String code;
    private final String message;
}
