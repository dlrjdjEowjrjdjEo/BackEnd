package com.medic.controller;

import com.medic.dto.CalendarReq;
import com.medic.dto.CalendarRes;
import com.medic.jpa.Calendar;
import com.medic.jpa.Routine;
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
		List<Calendar> calendarList = mypageService.getCalendarMonthList(userId, month);
		return new ResponseEntity<>(calendarList, HttpStatus.OK);
	}

	@GetMapping("/{userId}/{date}/{day}")
	public ResponseEntity<?> myDate(@PathVariable int userId, @PathVariable String date, @PathVariable String day) {
		List<CalendarRes> resultList = new ArrayList<CalendarRes>();
		List<Routine> routineList = mypageService.getRoutineListByDay(userId, day, date);
		List<Calendar> calendarList = mypageService.getCalendarDayList(userId, date);
		for (Routine r : routineList) {
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
			Boolean taken = false;
			for (Calendar c : calendarList) {
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
		Optional<Calendar> calendar = mypageService.getCalendar(calendarId);
		return new ResponseEntity<>(calendar, HttpStatus.OK);
	}
	
	@PostMapping("/{userId}/{date}")
	public ResponseEntity<?> insertCalendar(@RequestBody CalendarReq calendarReq) {
		Calendar calendar = Calendar.builder().routineId(calendarReq.getRoutineId()).userId(calendarReq.getUserId())
				.date(calendarReq.getDate()).taken(true).build();
		mypageService.insertCalendar(calendar);
		return new ResponseEntity<>(calendar.getCalendarId(), HttpStatus.OK);
	}

	@DeleteMapping("/{calendarId}")
	public ResponseEntity<?> deleteCalendar(@PathVariable int calendarId) {
		mypageService.deleteCalendar(calendarId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
