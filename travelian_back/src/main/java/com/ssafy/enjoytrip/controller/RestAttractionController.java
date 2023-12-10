package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;
import com.ssafy.enjoytrip.model.service.AttractionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/attraction")
public class RestAttractionController {
	private final AttractionService service;
	
	RestAttractionController(AttractionService service){
		this.service = service;
	}
	
	@GetMapping() 
	@ApiOperation(value = "모든 관광지의 정보를 반환한다.", response = AttractionInfo.class)
	public ResponseEntity<?> getAttractions(@RequestParam(value="offset", defaultValue="0") Integer offset){
		try {
			List<AttractionInfo> list = service.getAttractions(offset);
			return new ResponseEntity<List<AttractionInfo>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/search")
	@ApiOperation(value = "선택한 지역과 여행지 유형과 일치하는 관광지를 반환한다.")
	public ResponseEntity<?> getAttraction(
			@RequestParam(value="sidoCode", required=false) Integer sidoCode,
			@RequestParam(value="contentTypeId", required=false) Integer contentTypeId,
			@RequestParam(value="keyword", required=false) String keyword){
		try {
			Map<String, Object> searchParams = new HashMap<>();
			searchParams.put("sidoCode", sidoCode);
			searchParams.put("contentTypeId", contentTypeId);
			searchParams.put("keyword", keyword);
			List<AttractionInfo> list = service.getAttractions(searchParams);
			return new ResponseEntity<List<AttractionInfo>>(list, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// 검색 조건 조회 -> 프론트에서 처리

	// 카테고리별 조회
	@GetMapping("/category/{category}") 
	@ApiOperation(value = "카테고리별 관광지를 조회합니다.", response = AttractionInfo.class)
	public ResponseEntity<?> getAttractions(@PathVariable("category") String category, @RequestParam(value="offset", defaultValue="0") Integer offset){
		try {
			List<AttractionInfo> list = service.getCategoryAttractions(category, offset);
			return new ResponseEntity<List<AttractionInfo>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{contentId}") 
	@ApiOperation(value = "관광지 상세정보를 조회합니다.", response = AttractionInfo.class)
	public ResponseEntity<?> getAttraction(@PathVariable("contentId") Integer contentId){
		try {
			AttractionInfo attr = service.getAttraction(contentId);
			return new ResponseEntity<AttractionInfo>(attr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/like/{contentId}") 
	@ApiOperation(value = "관광지 좋아요 수 증가.")
	public ResponseEntity<?> likeAttraction(@PathVariable("contentId") Integer contentId){
		try {
			service.likeAttraction(contentId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
