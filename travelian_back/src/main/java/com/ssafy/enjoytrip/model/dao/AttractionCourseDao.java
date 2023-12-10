package com.ssafy.enjoytrip.model.dao;

import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionCourseDao {
    List<AttractionCourse> getAttractions(long courseId);
}
