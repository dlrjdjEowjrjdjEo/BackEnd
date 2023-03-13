package com.medic.service;

import com.todaypill.db.entity.Calendar;
import com.todaypill.db.entity.Like;
import com.todaypill.db.entity.Routine;
import com.todaypill.db.entity.User;
import com.todaypill.repository.CalendarRepository;
import com.todaypill.repository.LikeRepository;
import com.todaypill.repository.RoutineRepository;
import com.todaypill.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

	/*@Autowired
	private UserRepository userRepository;

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private RoutineRepository routineRepository;
	
	@Autowired
	private CalendarRepository calendarRepository;*/

	/*public MyPageService(UserRepository userRepository, LikeRepository likeRepository,
                         RoutineRepository routineRepository, CalendarRepository calendarRepository) {
		super();
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
		this.routineRepository = routineRepository;
	}

	@Transactional
	public List<Like> getLikeList(int userId) {
		return likeRepository.findAllByUserId(userId);
	}

	@Transactional
	public User getUser(int userId) {
		return userRepository.findOneByUserId(userId);
	}

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
	}*/
}
