package com.medic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateNameReq {
	@NotNull(message = "user_id를 입력하세요.")
	private int userId;

	@NotNull(message = "바꿀 이름을 입력하세요.")
	private String name;
}
