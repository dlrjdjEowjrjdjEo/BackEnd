package com.medic.userservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

public class HealthDTO {

    @Data
    @Builder
    public static class ReqGet {
        @NotNull(message = "이름을 입력하세요.")
        private String userName;

        @NotNull(message = "핸드폰 번호를 입력하세요.")
        private String phoneNumber;

        @NotNull(message = "생일을 입력하세요.")
        private String birthday;

        @NotNull(message = "이메일을 입력하세요.")
        private String email;
    }

    @Data
    @Builder
    public class ReqDetail {
        @NotNull(message = "알약 크기를 입력하세요.")
        private Boolean pillSize;

        @NotNull(message = "선호 브랜드를 입력하세요.")
        private String brand;

        @NotNull(message = "user id를 입력하세요.")
        private Integer userId;
    }
}
