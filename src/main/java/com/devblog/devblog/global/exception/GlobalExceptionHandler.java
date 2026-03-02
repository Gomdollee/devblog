package com.devblog.devblog.global.exception;

import com.devblog.devblog.global.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DevBlogException.class)
    public ApiResponse<?> handleDevBlogException(DevBlogException e) {

        return ApiResponse.fail(
                e.getErrorCode().getCode(),
                e.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {

        return ApiResponse.fail(
                ErrorCode.BAD_REQUEST.getCode(),
                e.getMessage()
        );
    }
}
