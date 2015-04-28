package org.beesden.ratings.dao;

import java.util.List;

import org.beesden.ratings.model.FilterReviews;
import org.beesden.ratings.model.entity.ReviewItem;

public interface ReviewDAO {

	/**
	 * Create a new review
	 *
	 * @param review
	 */
	public ReviewItem createReview(ReviewItem review);

	/**
	 * Delete a review
	 *
	 * @param review
	 */
	public void deleteReview(ReviewItem reviewItem);

	/**
	 * Get a specific review
	 *
	 * @param review
	 */
	public ReviewItem getReviewById(Integer reviewId);

	/**
	 * Get a filtered list of reviews
	 *
	 * @param review
	 */
	public List<ReviewItem> getReviews(FilterReviews filters);

	/**
	 * Update a review item
	 *
	 * @param review
	 */
	public void updateReview(ReviewItem review);

}