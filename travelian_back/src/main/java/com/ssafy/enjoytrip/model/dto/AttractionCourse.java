package com.ssafy.enjoytrip.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttractionCourse {
	private long acId;
	private long courseId; // 코스 아이디
	private int contentId; // 관광지 아이디
	private String title;
	private int order; // 순서
	private String userId; // 유저 정보
}
