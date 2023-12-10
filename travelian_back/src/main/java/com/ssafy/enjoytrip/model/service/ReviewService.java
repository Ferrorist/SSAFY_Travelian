package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.ReviewDao;
import com.ssafy.enjoytrip.model.dto.Review;

@Service
public class ReviewService {
	private final ReviewDao dao;

	ReviewService(ReviewDao dao) {
		this.dao = dao;
	}

	public List<Review> getReviews(int contentId) {
		List<Review> review = dao.getReviews(contentId);
		return review;
	}

	public Review getReview(long reviewId) {
		Review review = dao.getReview(reviewId);
		System.out.println(review.getContent());
		return review;
	}

	public void createReview(Review review) {
		review.setContent(review.getContent().replace("\r\n", "<br>"));
		dao.createReview(review);
	}

	public void putReview(long reviewId, Review review) {
		review.setReviewId(reviewId);
		dao.putReview(review);
	}

	public void deleteReview(long reviewId) {
		dao.deleteReview(reviewId);
	}
}
