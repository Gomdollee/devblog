package com.devblog.devblog.global.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 공통 응답 정택
 * - 모든 API의 HTTP 상태코드는 200
 * - 성공/실패 여부는 응답 바디의 success 필드로 구분함
 *  - { "success": true, "data": { ... } }
 *  - { "success": false, "error": { "code": "ERROR_CODE", "message": "오류 메시지" } }
 */

@Getter
@Builder
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ErrorResponse error;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail(String code, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .error(new ErrorResponse(code, message))
                .build();
    }

}
