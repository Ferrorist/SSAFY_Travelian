package com.ssafy.enjoytrip.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttractionCourseDto {
	private List<AttractionDto> attractions;
	private Course course;
}
