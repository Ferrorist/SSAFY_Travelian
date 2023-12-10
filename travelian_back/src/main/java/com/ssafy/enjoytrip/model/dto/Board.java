package com.ssafy.enjoytrip.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Board {
	private String articleNo;
	private String subject;
	private String content;
	private String userId;
	private String name;
	private String hit;
	private String registerTime;
}
