package com.ssafy.enjoytrip.util.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.AttractionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.context.Context;

@Configuration
public class MailUtil {
	final String ENCODING = "UTF-8";
	final String PORT = "465";
	final String SMTPHOST = "smtp.gmail.com";
	public void sendMail(Session session, String title, String receiver, List<AttractionCourse> attractions) {
		Message msg = new MimeMessage(session);

        try {
			msg.setFrom(new InternetAddress("example@gmail.com", "TRAVELIAN", ENCODING));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			msg.setSubject(title);
			msg.setContent(getContent(attractions), "text/html; charset=utf-8");
			Transport.send(msg);
			
			System.out.println("메일 보내기 성공");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("메일 보내기 실패");
		}
	}
	
	public Session setting(Properties props, String user_name, String password) {
		Session session = null;
		
		try {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTPHOST);
			props.put("mail.smtp.port", PORT);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", SMTPHOST);
			props.put("mail.smtp.starttls.required", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			props.put("mail.smtp.quit-wait", false);
			props.put("mail.smtp.socketFactory.port", PORT);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user_name, password);
				}
			});
		}catch(Exception e) {
			System.out.println("Session Setting 실패");
		}
		return session;
	}

	public String getContent(List<AttractionCourse> attractions){
		String content = "<head>\n" +
				"    <meta charset=\"UTF-8\" />\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
				"    <title>여행 코스 정보 \uD83C\uDF0D</title>\n" +
				"    <style>\n" +
				"      body {\n" +
				"        font-family: \"Arial\", sans-serif;\n" +
				"        margin: 20px;\n" +
				"        padding: 20px;\n" +
				"        background: linear-gradient(\n" +
				"          to right,\n" +
				"          #d76d77,\n" +
				"          #a28de7\n" +
				"        ); /* 연보라색 그라데이션 테마 설정 */\n" +
				"      }\n" +
				"      .container {\n" +
				"        max-width: 600px;\n" +
				"        margin: 0 auto;\n" +
				"        background-color: #fff;\n" +
				"        padding: 20px;\n" +
				"        border-radius: 8px;\n" +
				"        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
				"      }\n" +
				"      h1,\n" +
				"      h2,\n" +
				"      p {\n" +
				"        color: #333;\n" +
				"      }\n" +
				"      .highlight {\n" +
				"        color: #d76d77; /* 강조색 */\n" +
				"      }\n" +
				"      ul {\n" +
				"        list-style-type: none;\n" +
				"        padding: 0;\n" +
				"      }\n" +
				"      li {\n" +
				"        margin-bottom: 10px;\n" +
				"      }\n" +
				"      .divider {\n" +
				"        border-top: 2px solid #d76d77; /* 구분선 색상 */\n" +
				"        margin-top: 20px;\n" +
				"        margin-bottom: 20px;\n" +
				"      }\n" +
				"      .emoji {\n" +
				"        font-size: 1.2em;\n" +
				"        margin-right: 5px;\n" +
				"      }\n" +
				"    </style>\n" +
				"  </head>\n" +
				"  <body>\n" +
				"    <div class=\"container\">\n" +
				"      <h1>여행 코스 안내 \uD83D\uDDFA️</h1>\n<br/><br/>" +
				"      <p>안녕하세요! 여행 코스를 안내해드립니다. 아래는 여행 일정입니다.</p>\n <br/>" +
				"\n" +
				"      <h2>일정 \uD83D\uDCC5</h2>\n" +
				"      <ul>\n" ;
		for(AttractionCourse each : attractions){
			content += getCourseContent(each);
		}
		content += "        <!-- 필요에 따라 일정을 추가 또는 수정하세요 -->\n" +
				"      </ul>\n" +
				"\n" +
				"      <div class=\"divider\"></div>\n" +
				"      <!-- 구분선 추가 -->\n" +
				"\n" +
				"      <br/> <p>\n" +
				"        더 자세한 정보는 첨부된 지도를 확인하시거나 문의해주세요. 즐거운 여행\n" +
				"        되세요! \uD83C\uDF1F\n" +
				"      </p>\n" +
				"    </div>\n" +
				"  </body>";

		return content;
	}

	public String getCourseContent(AttractionCourse attraction){
		int order = attraction.getOrder() + 1;
		return "        <li>\n" +
				"          <span class=\"emoji\">\uD83D\uDDD3️</span\n" +
				"          ><a href=\"http://localhost:5173/detail/"+ attraction.getContentId() +"\"><span class=\"highlight\">"+ order +" 번째 여정 "+ ":</span> "+ attraction.getTitle() +"</a>"+
				"        </li>\n";
	}
}
