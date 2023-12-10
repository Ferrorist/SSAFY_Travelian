package com.ssafy.enjoytrip.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.Member;
import com.ssafy.enjoytrip.model.service.MemberService;
import com.ssafy.enjoytrip.util.MemberSorting;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class RestAdminController {
	private static final Logger logger = LoggerFactory.getLogger(RestAdminController.class);
	
	@Autowired
	private MemberService memberService;
	
	private MemberSorting sortUtil = MemberSorting.getInstance();
	
	@ApiOperation(value = "모든 사용자의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<?> getList() {
		try {
			List<Member> list = memberService.getList();
			
			if(list == null || list.size() == 0)
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "모든 사용자의 정보를 반환한다.", response = List.class)
	@GetMapping("{sort}/{desc}")
	public ResponseEntity<?> getList(@PathVariable("sort") String sort, @PathVariable("desc") String desc ) {
		try {
			boolean isDesc = desc.equals("true") ? true : false;

			List<Member> list = memberService.getList();
			
			switch(sort) {
			case "id":
				sortUtil.idTimSort(list, list.size(), isDesc);
				break;
			case "name":
				sortUtil.nameTimSort(list, list.size(), isDesc);
				break;
			default: 
				sortUtil.idTimSort(list, list.size(), isDesc);
				break;
			}
			
			if(list == null || list.size() == 0)
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@ApiOperation(value = "userId를 가진 사용자를 반환한다.", response = Member.class)
	@GetMapping("/{userId}")
	public ResponseEntity<?> searchMember(@PathVariable("userId") String userId){
		try {
			Member member = memberService.searchMember(userId);
			
			if(member != null)
				return new ResponseEntity<Member>(member, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@ApiOperation(value = "사용자를 DB에 등록한다.")
	@PostMapping
	public ResponseEntity<?> createMember(@RequestBody Member member){
		try {
			if(member.getIsAdmin() == "false")	member.setIsAdmin("0");
			else member.setIsAdmin("1");
			
			boolean create = memberService.create(member);
			
			if(create)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "사용자의 정보를 수정한다.")
	@PutMapping
	public ResponseEntity<?> updateMember(@RequestBody Member member){
		try {
			boolean update = memberService.modify(member);
			
			if(update) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "userId에 해당하는 사용자를 DB에서 삭제한다.")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteMember(@PathVariable("userId") String userId){
		try {
			boolean delete = memberService.delete(userId);
			
			if(delete)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
