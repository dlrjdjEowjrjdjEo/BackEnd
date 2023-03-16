package com.medic.service;

import com.medic.jpa.Calendar;
import com.medic.jpa.Routine;
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
	public List<Routine> getRoutineList(int userId) {
		return routineRepository.findAllByUserId(userId);
	}

	@Transactional
	public List<Routine> getRoutineListByDay(int userId, String day, String date) {
		return routineRepository.findAllByUserIdAndDay(userId, day, date);
	}

	@Transactional
	public List<Calendar> getCalendarMonthList(int userId, int month) {
		return calendarRepository.findAllByMonth(userId, month);
	}

	@Transactional
	public List<Calendar> getCalendarDayList(int userId, String date) {
		return calendarRepository.findAllByDate(userId, date);
	}

	@Transactional
	public Optional<Calendar> getCalendar(int calendarId) {
		return calendarRepository.findById(calendarId);
	}

	@Transactional
	public Calendar insertCalendar(Calendar calendar) {
		return calendarRepository.save(calendar);
	}

	@Transactional
	public void deleteCalendar(int calendarId) {
		calendarRepository.deleteCalendar(calendarId);
	}

	@Transactional
	public Routine insertRoutine(Routine routine) {
		return routineRepository.save(routine);
	}

	@Transactional
	public void updateRoutineVisibility(int routineId, String deletedSince) {
		Routine originalRoutine = routineRepository.findOneByRoutineId(routineId);
		originalRoutine.setDeletedSince(deletedSince);
		routineRepository.save(originalRoutine);
	}

	@Transactional
	public void updateRoutine(int routineId, Routine routine, String deletedSince) {
		Routine originalRoutine = routineRepository.findOneByRoutineId(routineId);
		originalRoutine.setDeletedSince(deletedSince);
//		originalRoutine.setTime(routine.getTime());
//		originalRoutine.setDay(routine.getDay());
//		originalRoutine.setTablets(routine.getTablets());
//		originalRoutine.setPushAlarm(routine.getPushAlarm());
		routineRepository.save(originalRoutine);
		routineRepository.save(routine);
	}
}
