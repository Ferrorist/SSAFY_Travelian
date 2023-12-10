package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.AttractionCourseDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.Course;
import com.ssafy.enjoytrip.model.service.CourseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/course")
public class RestCourseController {

	private final CourseService service;

	RestCourseController(CourseService service) {
		this.service = service;
	}

	@GetMapping()
	@ApiOperation(value = "여행 코스 전체 정보를 반환한다.", response = Course.class)
	public ResponseEntity<?> getCourses(@RequestParam(value = "offset", defaultValue = "0") Integer offset) {
		try {
			List<Course> list = service.getCourses(offset);
			return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search/{keyword}")
	@ApiOperation(value = "여행 코스 검색 정보를 반환한다.", response = Course.class)
	public ResponseEntity<?> getSearchCourses(@PathVariable("keyword") String keyword,
			@RequestParam(value= "option", defaultValue= "") String option,
			@RequestParam(value = "offset", defaultValue = "0") String offset) {
		try {
			keyword = keyword.trim();
			
			Map<String, Object> map = new HashMap<>();
			map.put("option", option);
			map.put("keyword", keyword); 
			map.put("offset", offset);
			System.out.println(map.toString());
			List<Course> list = service.getSearchCourses(map);
			return new ResponseEntity<List<Course>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{courseId}")
	@ApiOperation(value = "여행 코스 상세 정보를 반환한다.", response = Course.class)
	public ResponseEntity<?> getCourse(@PathVariable("courseId") long courseId) {
		try {
			Course course = service.getCourse(courseId);
			return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/attractions/{courseId}")
	@ApiOperation(value = "여행 코스에 등록된 관광지들을 반환한다.", response = AttractionDto.class)
	public ResponseEntity<?> getCourseAttraction(@PathVariable("courseId") long courseId) {
		try {
			List<AttractionDto> list = service.getCourseAttraction(courseId);
			return new ResponseEntity<List<AttractionDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/attractions")
	@ApiOperation(value = "여행 코스 정보를 등록한다.")
	@Transactional
	public ResponseEntity<Void> createCourse(@RequestBody AttractionCourseDto attrCourse) {
		try {
			service.createCourse(attrCourse.getCourse());
			Course c = service.getCourseByNameAndUserId(attrCourse.getCourse().getName(),
					attrCourse.getCourse().getUserId());
			service.createAttractionCourse(c.getCourseId(), attrCourse.getAttractions());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/like/{courseId}")
	@ApiOperation(value = "여행 코스 좋아요 수 증가.")
	public ResponseEntity<Void> likeCourse(@PathVariable("courseId") long courseId) {
		try {
			service.likeCourse(courseId);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{courseId}")
	@ApiOperation(value = "여행 코스 상세 정보를 삭제합니다.")
	public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") long courseId) {
		try {
			service.deleteCourse(courseId);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/mail/{courseId}")
	@ApiOperation(value = "여행 코스를 메일로 전송합니다.")
	public ResponseEntity<Void> sendCourse(@PathVariable("courseId") long courseId, @RequestBody String email) {
		try {
			service.sendCourse(courseId, email);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
