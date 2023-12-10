package com.ssafy.enjoytrip.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Member {
	private String userId;
	private String password;
	private String name;
	private String emailId;
	private String emailDomain;
	private String registerTime;
	private String isAdmin;
}
