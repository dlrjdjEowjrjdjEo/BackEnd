package com.medic.service;

import com.medic.jpa.CommonQuestion;
import com.medic.jpa.CommonQuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CommonQuestionServiceImpl implements CommonQuestionService {

	CommonQuestionRepository commonQuestionRepository;

	@Autowired
	public CommonQuestionServiceImpl(CommonQuestionRepository commonQuestionRepository) {
		this.commonQuestionRepository = commonQuestionRepository;
	}

	@Transactional
	public CommonQuestion findOneByUserId(int userId) {
		return commonQuestionRepository.findOneByUserId(userId);
	}

	@Transactional
	public void updateVitaminD(int userId) {
		commonQuestionRepository.updateVitaminD(userId);
	}

	@Transactional
	public void updateMultivitamin(int userId) {
		commonQuestionRepository.updateMultivitamin(userId);
	}

	@Transactional
	public void updateFe(int userId) {
		commonQuestionRepository.updateFe(userId);
	}

	@Transactional
	public void updateVitaminC(int userId) {
		commonQuestionRepository.updateVitaminC(userId);
	}

	@Transactional
	public void updateOmega3(int userId) {
		commonQuestionRepository.updateOmega3(userId);
	}

	@Transactional
	public void updateMagnesium(int userId) {
		commonQuestionRepository.updateMagnesium(userId);
	}

	@Transactional
	public void updateVitaminB(int userId) {
		commonQuestionRepository.updateVitaminB(userId);
	}

	@Transactional
	public void updateCollagen(int userId) {
		commonQuestionRepository.updateCollagen(userId);
	}

	@Transactional
	public void createCq(int userId) {
		CommonQuestion cq = CommonQuestion.builder()
				.userId(userId)
				.pregnant(false)
				.smoking(false)
				.allergy("")
				.outdoor_activity(3)
				.balanced_meal(false)
				.lack("")
				.is_ok_big_pill(false)
				.heartburn(false)
				.constipation(	false)
				.diarrhea(	false)
				.digestiveDisorder(	false)
				.migraine(false)
				.backache(	false)
				.bowelSyndrome(	false)
				.atopy(false)
				.dandruff(	false)
				.stomatitis(false)
				.legCramp(false)
				.anemia(false)
				.thyroidDisease(false)
				.kidney_disease(false)
				.diabetes(false)
				.gouty(false)
				.highBloodPressure(false)
				.hyperlipidemia(false)
				.periodontitis(false)
				.heartFailure(false)
				.contraceptive(false)
				.antacid(false)
				.bloodPressureMedicine(false)
				.diuretic(false)
				.sotalol(false)
				.gabapentin(false)
				.levothyroxine(false)
				.antibiotics(false)
				.physicalActivity(false)
				.preferred_brand("")
				.problem("")
				.build();
		commonQuestionRepository.save(cq);
	}

}
