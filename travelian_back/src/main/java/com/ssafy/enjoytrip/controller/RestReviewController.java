package com.ssafy.enjoytrip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.Review;
import com.ssafy.enjoytrip.model.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/review")
public class RestReviewController {

	private final ReviewService service;

	RestReviewController(ReviewService service) {
		this.service = service;
	}

	@GetMapping("/list/{contentId}")
	@ApiOperation(value = "관광지의 후기 정보들을 가져옵니다.", response = Review.class)
	public ResponseEntity<?> getReviews(@PathVariable("contentId") int contentId) {
		try {
			List<Review> reviews = service.getReviews(contentId);
			return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/articleNo/{reviewId}")
	@ApiOperation(value = "관광지의 후기 단일 정보를 가져옵니다.", response = Review.class)
	public ResponseEntity<?> getReview(@PathVariable("reviewId") long reviewId) {
		try {
			Review review = service.getReview(reviewId);

			return new ResponseEntity<Review>(review, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	@ApiOperation(value = "관광지의 후기를 등록합니다.")
	public ResponseEntity<?> getReview(@RequestBody Review review) {
		try {
			service.createReview(review);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{reviewId}")
	@ApiOperation(value = "관광지의 후기를 수정합니다.", response = Review.class)
	public ResponseEntity<?> putReview(@PathVariable("reviewId") long reviewId, @RequestBody Review review) {
		try {
			service.putReview(reviewId, review);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{reviewId}")
	@ApiOperation(value = "관광지의 후기를 삭제합니다.")
	public ResponseEntity<?> deleteReview(@PathVariable("reviewId") long reviewId) {
		try {
			service.deleteReview(reviewId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
