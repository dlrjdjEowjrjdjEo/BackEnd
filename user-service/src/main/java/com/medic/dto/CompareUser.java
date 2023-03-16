package com.medic.dto;

import lombok.Data;

@Data
public class CompareUser implements Comparable<CompareUser> {
	private Integer userId;
	private Integer cnt;

	public CompareUser(Integer userId, Integer cnt) {
		this.userId = userId;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(CompareUser cu) {
		return cu.cnt - this.cnt;
	}
}
