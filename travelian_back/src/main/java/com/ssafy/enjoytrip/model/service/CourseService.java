package com.ssafy.enjoytrip.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.AttractionCourseDao;
import com.ssafy.enjoytrip.model.dao.CourseDao;
import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.Course;
import com.ssafy.enjoytrip.util.email.MailSender;

@Service
public class CourseService {
	private final CourseDao dao;
	private final AttractionCourseDao acDao;
	private final MailSender mailSender;
	
	CourseService(CourseDao dao, MailSender mailSender, AttractionCourseDao acDao){
		this.dao = dao;
		this.acDao = acDao;
		this.mailSender = mailSender;
	}
	
	public List<Course> getCourses(Integer offset){
		return dao.getCourses(offset);
	}
	
	public List<Course> getSearchCourses(Map<String, Object> map){
		return dao.getSearchCourses(map);
	}
	
	public Course getCourse(long courseId) {
		return dao.getCourse(courseId);
	}
	
	public void createCourse(Course course) {
		 dao.createCourse(course);
	}
	
	public void createAttractionCourse(long courseId, List<AttractionDto> attractions) {
		for(AttractionDto attr : attractions) {
			AttractionCourse attrCourse = new AttractionCourse();
			attrCourse.setContentId(attr.getContentId());
			attrCourse.setCourseId(courseId);
			attrCourse.setTitle(attr.getTitle());
			attrCourse.setUserId(attr.getUserId());
			attrCourse.setOrder(attr.getOrder());
			System.out.println(attrCourse);
			dao.createAttractionCourse(attrCourse);
		}
	}
	
	public void likeCourse(long courseId) {
		dao.likeCourse(courseId);
	}
	
	public void deleteCourse(long courseId) {
		dao.deleteCourse(courseId);
	}
	
	public void sendCourse(long courseId, String receiver) {
		List<AttractionCourse> attractions = acDao.getAttractions(courseId);
		mailSender.send(receiver, attractions);
	}
	
	public Course getCourseByNameAndUserId(String name, String userId) {
		return dao.getCourseByNameAndUserId(name, userId);
	}

	// 코스에 등록된 관광지 반환.
	public List<AttractionDto> getCourseAttraction(long courseId) {
		return dao.getCourseAttraction(courseId);
	}
}
