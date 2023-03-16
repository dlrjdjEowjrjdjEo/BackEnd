package com.medic.jpa;

import com.medic.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer>{
	List<LikeEntity> findAllByUserId(int userId);
	
	List<LikeEntity> findAllBySupplementId(int supplementId);
	
	@Query(value = "select * from `like` where user_id = ?1 and supplement_id = ?2", nativeQuery = true)
	List<LikeEntity> likeClickOrNot(int userId, int supplementId);
	
	@Query(value = "delete * from `like` where like_id = ?1", nativeQuery = true)
	void deleteLike(int likeId);
}
