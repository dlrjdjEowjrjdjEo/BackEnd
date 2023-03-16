package com.medic.service;

import com.medic.dto.HealthDTO;
import com.medic.dto.UserDTO;
import com.medic.entity.CommonQuestionEntity;
import com.medic.entity.LikeEntity;
import com.medic.entity.UserEntity;

import java.util.List;

public interface UserService  {
    Boolean signup(String email, String name, int age, String gender);
    UserEntity findOneByUserId(int userId);
    UserEntity findOneByEmail(String email);
    void updateRecommend(String email, String recoOne, String recoTwo, String recoThr);
//    Like insertLike(int userId, int supplementId);
//    void deleteLike(int userId, int supplementId);
    List<LikeEntity> getUserLike(int userId);
    List<Integer> likeListOfSupplement(int supplementId);
    void updateName(UserDTO.ReqUpdateName updateNameReq);
    String[] userFirstSurvey(UserDTO.ReqFirstSurvey userFirstSurveyReq);
    void insertDetail(HealthDTO.ReqDetail detailHealthReq);
    void patchGender(String email, String gender);
    List<UserDTO.CompareUser> calcSimilarity(CommonQuestionEntity cq, int age, String gender, int uuId);
}
