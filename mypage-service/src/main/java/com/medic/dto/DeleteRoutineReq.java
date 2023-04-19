package com.medic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteRoutineReq {
	@NotNull(message = "삭제 일자를 입력하세요.")
	private String deletedSince;
}
