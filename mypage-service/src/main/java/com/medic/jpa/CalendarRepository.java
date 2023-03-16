package com.medic.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
	@Query(value = "select * from calendar where user_id = ?1 and month(date) = ?2", nativeQuery = true)
	List<Calendar> findAllByMonth(int userId, int month);
	
	@Query(value = "select * from `calendar` where user_id = ?1 and date = ?2", nativeQuery = true)
	List<Calendar> findAllByDate(int userId, String date);
	
	@Modifying
	@Query(value = "delete from `calendar` where calendar_id = ?1", nativeQuery = true)
	void deleteCalendar(int calendarId);
}
