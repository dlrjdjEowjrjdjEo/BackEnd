package com.medic.service;

import com.medic.dto.HealthDTO;
import com.medic.dto.UpdateNameReq;
import com.medic.dto.UserFirstSurveyReq;
import com.medic.jpa.CommonQuestion;
import com.medic.jpa.CompareUser;
import com.medic.jpa.Like;
import com.medic.jpa.UserEntity;

import java.util.List;

public interface UserService  {
    Boolean signup(String email, String name, int age, String gender);
    UserEntity findOneByUserId(int userId);
    UserEntity findOneByEmail(String email);
    void updateRecommend(String email, String recoOne, String recoTwo, String recoThr);
//    Like insertLike(int userId, int supplementId);
//    void deleteLike(int userId, int supplementId);
    List<Like> getUserLike(int userId);
    List<Integer> likeListOfSupplement(int supplementId);
    void updateName(UpdateNameReq updateNameReq);
    String[] userFirstSurvey(UserFirstSurveyReq userFirstSurveyReq);
    void insertDetail(HealthDTO.ReqDetail detailHealthReq);
    void patchGender(String email, String gender);
    List<CompareUser> calcSimilarity(CommonQuestion cq, int age, String gender, int uuId);
}
