package com.medic.service;

import com.medic.entity.CommonQuestionEntity;

public interface CommonQuestionService {

    CommonQuestionEntity findOneByUserId(int userId);
    void updateVitaminD(int userId);
    void updateMultivitamin(int userId);
    void updateFe(int userId);
    void updateVitaminC(int userId);
    void updateOmega3(int userId);
    void updateMagnesium(int userId);
    void updateVitaminB(int userId);
    void updateCollagen(int userId);
    void createCq(int userId);

}
