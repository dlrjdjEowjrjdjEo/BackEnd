package com.medic.controller;

import com.medic.dto.CalendarReq;
import com.medic.dto.CalendarRes;
import com.medic.entity.CalendarEntity;
import com.medic.entity.RoutineEntity;
import com.medic.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

	@GetMapping("/check")
	public String check() {
		return "Hello MyPage Controller";
	}

	private final MypageService mypageService;

	@Autowired
	public CalendarController(MypageService mypageService) {
		this.mypageService = mypageService;
	}


	@GetMapping("/{userId}/{month}")
	public ResponseEntity<?> calendar(@PathVariable int userId, @PathVariable int month) {
		List<CalendarEntity> calendarEntityList = mypageService.getCalendarMonthList(userId, month);
		return new ResponseEntity<>(calendarEntityList, HttpStatus.OK);
	}

	@GetMapping("/{userId}/{date}/{day}")
	public ResponseEntity<?> myDate(@PathVariable int userId, @PathVariable String date, @PathVariable String day) {
		List<CalendarRes> resultList = new ArrayList<>();
		List<RoutineEntity> routineEntityList = mypageService.getRoutineListByDay(userId, day, date);
		List<CalendarEntity> calendarEntityList = mypageService.getCalendarDayList(userId, date);
		for (RoutineEntity r : routineEntityList) {
			CalendarRes res = new CalendarRes();
			res.setRoutineId(r.getRoutineId());
			res.setUserId(r.getUserId());
			res.setSupplementId(r.getSupplementId());
			res.setTime(r.getTime());
			res.setDay(r.getDay());
			res.setTablets(r.getTablets());
			res.setAddedSince(r.getAddedSince());
			res.setDeletedSince(r.getDeletedSince());
			res.setPushAlarm(r.getPushAlarm());
			
			int routineId = r.getRoutineId();
			boolean taken = false;
			for (CalendarEntity c : calendarEntityList) {
				if (c.getRoutineId() == routineId)
					taken = true;
				res.setCalendarId(c.getCalendarId());
			}
			res.setTaken(taken);
			resultList.add(res);
		}
		return new ResponseEntity<>(resultList, HttpStatus.OK);
	}

	@GetMapping("/{calendarId}")
	public ResponseEntity<?> getCalendar(@PathVariable int calendarId) {
		Optional<CalendarEntity> calendar = mypageService.getCalendar(calendarId);
		return new ResponseEntity<>(calendar, HttpStatus.OK);
	}
	
	@PostMapping("/{userId}/{date}")
	public ResponseEntity<?> insertCalendar(@RequestBody CalendarReq calendarReq) {
		CalendarEntity calendarEntity = CalendarEntity.builder().routineId(calendarReq.getRoutineId()).userId(calendarReq.getUserId())
				.date(calendarReq.getDate()).taken(true).build();
		mypageService.insertCalendar(calendarEntity);
		return new ResponseEntity<>(calendarEntity.getCalendarId(), HttpStatus.OK);
	}

	@DeleteMapping("/{calendarId}")
	public ResponseEntity<?> deleteCalendar(@PathVariable int calendarId) {
		mypageService.deleteCalendar(calendarId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
