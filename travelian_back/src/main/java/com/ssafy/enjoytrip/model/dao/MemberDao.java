package com.ssafy.enjoytrip.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.Member;

@Mapper
public interface MemberDao {
	
	int idCheck(String userId);
	
	int regist(Member member);
	
	List<Member> getList();

	int insertMember(Member m);

	int updateMember(Member member);
	
	int deleteMember(String userId);

	Member searchMember(String userId);

	Member loginUser(Member user);
	
	Member findPwd(Member user);
}
