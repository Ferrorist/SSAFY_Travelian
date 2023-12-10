package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.model.dto.Member;

public interface MemberService {

	List<Member> getList();

	boolean idCheck(String userId);

	boolean regist(Member member);

	boolean create(Member member);

	boolean modify(Member member);

	Member searchMember(String userId);

	boolean delete(String userId);

	Member loginUser(Member user);
	
	Member findPwd(Member user);
}
