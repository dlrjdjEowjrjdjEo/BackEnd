package com.medic.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @Data
    public static class ReqUpdateName {
        @NotNull(message = "user_id를 입력하세요.")
        private int userId;

        @NotNull(message = "바꿀 이름을 입력하세요.")
        private String name;
    }

    @Data
    public static class ReqFirstSurvey {

        @NotNull(message = "user id를 입력하세요.")
        private Integer userId;

        @NotNull(message = "임신 여부 입력하세요.")
        private boolean pregnant;

        @NotNull(message = "폐경기 여부 입력하세요.")
        private boolean menopause;

        @NotNull(message = "흡연 여부 입력하세요.")
        private boolean smoking;

        @NotNull(message = "음주 여부 입력하세요.")
        private Integer drink;

        @NotNull(message = "알러지 입력하세요.")
        private String allergy;

        @NotNull(message = "바깥활동 여부 입력하세요.")
        private Integer outdoor_activity;

        @NotNull(message = "균형식사 입력하세요.")
        private boolean balanced_meal;

        @NotNull(message = "뭐가 부족한지 입력하세요.")
        private String lack;

        @NotNull(message = "큰 알약은 괜찮은지 입력하세요.")
        private boolean is_ok_big_pill;

        @NotNull(message = "증상 뭐있는지 입력하세요.")
        private String symptom;

        @NotNull(message = "병 뭐있는지 입력하세요.")
        private String disease;

        @NotNull(message = "복용중인 약 입력하세요.")
        private String medicine;

        @NotNull(message = "격렬한 신체활동을 하는지 입력하세요.")
        private boolean toughActivity;

        @NotNull(message = "선호 브랜드 이름 입력하세요.")
        private String preferred_brand;

        @NotNull(message = "고민거리 입력하세요.")
        private String problem;
    }

    @Data
    public static class CompareUser implements Comparable<CompareUser> {
        private Integer userId;
        private Integer cnt;

        public CompareUser(Integer userId, Integer cnt) {
            this.userId = userId;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(CompareUser cu) {
            return cu.cnt - this.cnt;
        }
    }
}
