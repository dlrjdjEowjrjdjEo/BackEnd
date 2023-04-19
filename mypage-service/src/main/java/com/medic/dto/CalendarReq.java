package com.medic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CalendarReq {
	@NotNull(message = "날짜를 입력하세요.")
	private String date;

	@NotNull(message = "routineId를 입력하세요.")
	private int routineId;

	@NotNull(message = "userId를 입력하세요.")
	private int userId;
}
