package com.medic.userservice.service;

import com.medic.userservice.entity.UserEntity;

import java.util.List;

public interface UserService  {
    Boolean signup(String email, String name, int age, String gender);
    UserEntity findOneByUserId(int userId);
    void updateRecommend(String email, String recoOne, String recoTwo, String recoThr);
    Like insertLike(int userId, int supplementId);
    void deleteLike(int userId, int supplementId);
    List<Like> getUserLike(int userId);
    List<Integer> likeListOfSupplement(int supplementId);
    UserEntity findOneByEmail(String email);
    UserEntity findOneByUserId(int userId);
    void updateName(UpdateNameReq updateNameReq);
    String[] userFirstSurvey(UserFirstSurveyReq userFirstSurveyReq);
    void insertDetail(DetailHealthReq detailHealthReq);
    void patchGender(String email, String gender);
    List<CompareUser> calcSimilarity(CommonQuestion cq, int age, String gender, int uuId);
}
