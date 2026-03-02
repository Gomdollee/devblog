package com.devblog.devblog.global.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void succes_응답_생성_확인() {

        String data = "success";

        ApiResponse<String> response = ApiResponse.success(data);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo("success");
        assertThat(response.getError()).isNull();
    }

    @Test
    void fail_응답_생서_확인() {

        ApiResponse<Object> response = ApiResponse.fail("BAD_REQUEST", "잘못된 요청");

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getData()).isNull();
        assertThat(response.getError().getCode()).isEqualTo("BAD_REQUEST");
        assertThat(response.getError().getMessage()).isEqualTo("잘못된 요청");

    }
 }
