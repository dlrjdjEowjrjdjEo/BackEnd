package com.medic.dto;

import com.medic.entity.RoutineEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRes extends RoutineEntity {
	private Boolean taken;
	private int calendarId;

	public CalendarRes(int routineId, int userId, int supplementId, String time, String day, int tablets,
                       String addedSince, String deletedSince, Boolean pushAlarm, Boolean taken, int calendarId) {
		super(routineId, userId, supplementId, time, day, tablets, addedSince, deletedSince, pushAlarm);
		this.taken = taken;
		this.calendarId = calendarId;
	}
}
