package com.devblog.devblog.global.exception;

import lombok.Getter;

@Getter
public class DevBlogException extends RuntimeException{

    private final ErrorCode errorCode;

    public DevBlogException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DevBlogException(ErrorCode errorCode, String message, ErrorCode errorCode1) {
        this.errorCode = errorCode1;
    }
}
