package com.ssafy.enjoytrip.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import com.ssafy.enjoytrip.model.dto.AttractionInfo;

@Mapper
public interface AttractionDao {
	List<AttractionInfo> getAttractions(Integer offset);
	List<AttractionInfo> getCategoryAttractions(String category, Integer offset);
	AttractionInfo getAttraction(Integer contentId);
	void likeAttraction(long contentId);
	List<AttractionInfo> getSearchAttractions(Map<String, Object> searchParams);
}
