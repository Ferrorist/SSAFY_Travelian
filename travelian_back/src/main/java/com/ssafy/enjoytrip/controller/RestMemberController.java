package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.Member;
import com.ssafy.enjoytrip.model.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class RestMemberController {
	private static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value = "입력된 ID가 존재하는 지 여부를 체크한다. 존재할 경우 404 에러를 반환한다.")
	@PostMapping("/Check")
	public ResponseEntity<?> idCheck(@RequestBody String userId){
		try {
			boolean check = memberService.idCheck(userId);
			logger.debug("check : {}", check);
			
			if(check)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "회원가입을 실행한다.")
	@PostMapping("/register")
	public ResponseEntity<?> registUser(@RequestBody Member user) {
		try {
			boolean regist = memberService.regist(user);
			
			if(regist)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "비밀번호 찾기를 실행한다.")
	@PostMapping("/pwd")
	public ResponseEntity<?> findPwd(@RequestBody Member user) {
		try {
			Member member = memberService.findPwd(user);
			return new ResponseEntity<String>(member.getPassword(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
		
	@ApiOperation(value = "입력받은 ID와 비밀번호를 이용하여 사용자를 반환한다.")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member user){
		try {			
			logger.debug("try to login : {}", user);
			Member member = memberService.loginUser(user);
			logger.debug("login member : {}", member);
			if(member != null)
				return new ResponseEntity<Member>(member, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
