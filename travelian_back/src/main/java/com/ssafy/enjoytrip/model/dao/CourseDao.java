package com.ssafy.enjoytrip.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.Course;

@Mapper
public interface CourseDao {
	List<Course> getCourses(Integer offset);
	List<Course> getSearchCourses(Map<String, Object> map);
	Course getCourse(long courseId);
	void createCourse(Course course);
	void createAttractionCourse(AttractionCourse attr);
	void likeCourse(long courseId);
	void deleteCourse(long courseId);
	Course getCourseByNameAndUserId(String name, String userId);
	List<AttractionDto> getCourseAttraction(long courseId);
}
