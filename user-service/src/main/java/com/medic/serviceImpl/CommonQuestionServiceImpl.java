package com.medic.serviceImpl;

import com.medic.entity.CommonQuestionEntity;
import com.medic.jpa.CommonQuestionRepository;
import com.medic.service.CommonQuestionService;
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
	public CommonQuestionEntity findOneByUserId(int userId) {
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
		CommonQuestionEntity cq = CommonQuestionEntity.builder()
				.userId(userId)
				.pregnant(false)
				.smoking(false)
				.allergy("")
				.outdoorActivity(3)
				.balancedMeal(false)
				.lack("")
				.isOkBigPill(false)
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
				.kidneyDisease(false)
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
				.preferredBrand("")
				.problem("")
				.build();
		commonQuestionRepository.save(cq);
	}

}
