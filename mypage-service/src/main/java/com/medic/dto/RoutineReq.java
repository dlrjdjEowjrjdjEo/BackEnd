package com.medic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoutineReq {

	@NotNull(message = "영양제 id를 입력하세요.")
	private int supplementId;

	@NotNull(message = "시간을 입력하세요.")
	private String time;

	@NotNull(message = "요일을 입력하세요.")
	private String day;
	
	@NotNull(message = "개수를 입력하세요.")
	private int tablets;
	
	@NotNull(message = "true / false 를 입력하세요.")
	private Boolean pushAlarm;
	
	@NotNull(message = "추가일자를 입력하세요.")
	private String addedSince;
}
