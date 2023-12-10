package com.ssafy.enjoytrip.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 코스에 저장되는 객체
@Getter @Setter @ToString
public class AttractionDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int readcount;
	private int sidoCode;
	private int gugunCode;
	private double latitude; // mapy
	private double longitude; // mapx
	private String mlevel;
	private int order;
	private String userId;
}