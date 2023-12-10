package com.ssafy.enjoytrip.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Review {
	private long reviewId;
	private int contentId;
	private String userId;
	private String content;
	private String subject;
}
