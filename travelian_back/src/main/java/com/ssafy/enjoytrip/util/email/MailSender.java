package com.ssafy.enjoytrip.util.email;

import java.util.List;
import java.util.Properties;

import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class MailSender {
	private final MailUtil util;

	public MailSender(MailUtil util) {
		this.util = util;
	}
	private final String title = "여행 코스를 공유드립니다";
	private final String content = "여정의 시작";
	@Value("${email.username}")
	public String user_name;
	@Value("${email.password}")
	public String password;
	
	public void send(String receiver, List<AttractionCourse> attractions) {
		util.sendMail(util.setting(new Properties(), user_name, password), title, receiver, attractions);
	}
	
}
