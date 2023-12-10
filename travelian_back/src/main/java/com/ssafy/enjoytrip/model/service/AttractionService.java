package com.ssafy.enjoytrip.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.AttractionDao;
import com.ssafy.enjoytrip.model.dto.AttractionCourse;
import com.ssafy.enjoytrip.model.dto.AttractionInfo;

@Service
public class AttractionService {
	
	private final AttractionDao dao;
	
	AttractionService(AttractionDao dao){
		this.dao = dao;
	}
	
	public List<AttractionInfo> getAttractions(Integer offset){
		return dao.getAttractions(offset);
	}
	
	public List<AttractionInfo> getCategoryAttractions(String category, Integer offset){
		return dao.getCategoryAttractions(category, offset);
	}
	
	public AttractionInfo getAttraction(Integer contentId) {
		return dao.getAttraction(contentId);
	}

	public void likeAttraction(long contentId){
		dao.likeAttraction(contentId);
	}

	public List<AttractionInfo> getAttractions(Map<String, Object> searchParams) {
		return dao.getSearchAttractions(searchParams);
	}
}
