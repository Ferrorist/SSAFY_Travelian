package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.MemberDao;
import com.ssafy.enjoytrip.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public List<Member> getList() {
		return memberDao.getList();
	}

	@Override
	public boolean idCheck(String userId) {
		return memberDao.idCheck(userId) == 1;
	}

	@Override
	public boolean regist(Member member) {
		return memberDao.regist(member) == 1;
	}

	@Override
	public boolean create(Member member) {
		return memberDao.insertMember(member) == 1;
	}

	@Override
	public boolean modify(Member member) {
		return memberDao.updateMember(member) == 1;
	}

	@Override
	public Member searchMember(String userId) {
		return memberDao.searchMember(userId);
	}

	@Override
	public boolean delete(String userId) {
		return memberDao.deleteMember(userId) == 1;
	}

	@Override
	public Member loginUser(Member user) {
		return memberDao.loginUser(user);
	}
	
	@Override
	public Member findPwd(Member user) {
		return memberDao.findPwd(user);
	}

	
}
