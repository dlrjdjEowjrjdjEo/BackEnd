package com.medic.dto;

import lombok.Data;

public class SurveyDTO {

    @Data
    public static class ResCommon {
        private String allergy;
        private Boolean balancedMeal;
        private String disease;
        private Integer drink;
        private Boolean isOkBigPill;
        private String lack;
        private String medicine;
        private Boolean menopause;
        private Integer outdoorActivity;
        private String preferredBrand;
        private Boolean pregnant;
        private String problem;
        private Boolean smoking;
        private String symptom;
        private Boolean toughActivity;
        private Integer userId;
    }


}
