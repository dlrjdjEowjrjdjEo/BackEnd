package com.medic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InsertLikeReq {
	@NotNull(message = "user id를 입력하세요.")
	private int userId;

	@NotNull(message = "supplement id를 입력하세요.")
	private int supplementId;


}
