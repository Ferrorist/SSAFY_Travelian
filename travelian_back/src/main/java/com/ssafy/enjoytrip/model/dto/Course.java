package com.ssafy.enjoytrip.model.dto;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Course {
	private long courseId;
	private String name; //코스명
	private String date; //몇박몇일
	private String location;
	private String comment;//한줄평
	private String userId; //사용자
	private boolean isValid; //유효한데이터인지
	private int likes; //좋아요 수
}
