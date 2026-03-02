package com.devblog.devblog.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공통 응답 정택
 * - 모든 API의 HTTP 상태코드는 200
 * - 성공/실패 여부는 응답 바디의 success 필드로 구분함
 *  - { "success": true, "data": { ... } }
 *  - { "success": false, "error": { "code": "ERROR_CODE", "message": "오류 메시지" } }
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;

}
