package com.medic.jpa;

import com.medic.entity.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<RoutineEntity, Integer>{
	List<RoutineEntity> findAllByUserId(int userId);
	
	@Query(value = "select * from `routine` where user_id = ?1 and day like %?2% and ((added_since <= ?3 and ?3 < deleted_since) or (added_since <= ?3 and deleted_since is null));", nativeQuery = true)
	List<RoutineEntity> findAllByUserIdAndDay(int userId, String day, String date);
	
	RoutineEntity findOneByRoutineId(int routineId);
}
