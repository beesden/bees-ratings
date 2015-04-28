package org.beesden.ratings.service;

import java.util.List;

import org.beesden.ratings.model.FilterReviews;
import org.beesden.ratings.model.ReviewForm;
import org.beesden.ratings.model.entity.ReviewItem;
import org.beesden.ratings.utils.constants.ReviewStatus;

public interface ReviewService {

	ReviewItem createReview(ReviewForm review);

	Boolean deleteReview(Integer reviewId);

	List<ReviewItem> fetchReviews(FilterReviews filters);

	ReviewItem updateReview(Integer reviewId, ReviewForm reviewForm);

	ReviewItem updateReview(Integer reviewId, ReviewStatus status);

}