package com.ssafy.enjoytrip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.Review;

@Mapper
public interface ReviewDao {
	List<Review> getReviews(int contentId);
	Review getReview(long reviewId);
	void createReview(Review review);
	void putReview(Review review);
	void deleteReview(long reviewId);
	
}
