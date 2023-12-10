package com.ssafy.enjoytrip.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttractionDescription {
	private int contentId;
	private String homepage;
	private String overview;
	private String telname;
}
