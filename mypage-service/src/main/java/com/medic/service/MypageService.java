package com.medic.service;

import com.medic.entity.CalendarEntity;
import com.medic.entity.RoutineEntity;
import com.medic.jpa.RoutineRepository;
import com.medic.jpa.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MypageService {

	private final RoutineRepository routineRepository;
	private final CalendarRepository calendarRepository;

    @Autowired
    public MypageService(RoutineRepository routineRepository, CalendarRepository calendarRepository) {
		this.routineRepository = routineRepository;
		this.calendarRepository = calendarRepository;
    }

//    @Transactional
//	public List<Like> getLikeList(int userId) {
//		return likeRepository.findAllByUserId(userId);
//	}

//	@Transactional
//	public User getUser(int userId) {
//		return userRepository.findOneByUserId(userId);
//	}

	@Transactional
	public List<RoutineEntity> getRoutineList(int userId) {
		return routineRepository.findAllByUserId(userId);
	}

	@Transactional
	public List<RoutineEntity> getRoutineListByDay(int userId, String day, String date) {
		return routineRepository.findAllByUserIdAndDay(userId, day, date);
	}

	@Transactional
	public List<CalendarEntity> getCalendarMonthList(int userId, int month) {
		return calendarRepository.findAllByMonth(userId, month);
	}

	@Transactional
	public List<CalendarEntity> getCalendarDayList(int userId, String date) {
		return calendarRepository.findAllByDate(userId, date);
	}

	@Transactional
	public Optional<CalendarEntity> getCalendar(int calendarId) {
		return calendarRepository.findById(calendarId);
	}

	@Transactional
	public CalendarEntity insertCalendar(CalendarEntity calendarEntity) {
		return calendarRepository.save(calendarEntity);
	}

	@Transactional
	public void deleteCalendar(int calendarId) {
		calendarRepository.deleteCalendar(calendarId);
	}

	@Transactional
	public RoutineEntity insertRoutine(RoutineEntity routineEntity) {
		return routineRepository.save(routineEntity);
	}

	@Transactional
	public void updateRoutineVisibility(int routineId, String deletedSince) {
		RoutineEntity originalRoutineEntity = routineRepository.findOneByRoutineId(routineId);
		originalRoutineEntity.setDeletedSince(deletedSince);
		routineRepository.save(originalRoutineEntity);
	}

	@Transactional
	public void updateRoutine(int routineId, RoutineEntity routineEntity, String deletedSince) {
		RoutineEntity originalRoutineEntity = routineRepository.findOneByRoutineId(routineId);
		originalRoutineEntity.setDeletedSince(deletedSince);
//		originalRoutine.setTime(routine.getTime());
//		originalRoutine.setDay(routine.getDay());
//		originalRoutine.setTablets(routine.getTablets());
//		originalRoutine.setPushAlarm(routine.getPushAlarm());
		routineRepository.save(originalRoutineEntity);
		routineRepository.save(routineEntity);
	}
}
