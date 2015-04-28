package org.beesden.ratings.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.beesden.ratings.dao.ReviewDAO;
import org.beesden.ratings.model.FilterReviews;
import org.beesden.ratings.model.ReviewForm;
import org.beesden.ratings.model.entity.ReviewItem;
import org.beesden.ratings.service.ReviewService;
import org.beesden.ratings.utils.constants.HistoryType;
import org.beesden.ratings.utils.constants.ReviewAction;
import org.beesden.ratings.utils.constants.ReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	static final Logger logger = Logger.getLogger(ReviewServiceImpl.class);

	@Autowired
	private ReviewDAO reviewDAO;

	@Override
	public ReviewItem createReview(ReviewForm review) {
		logger.debug("Adding a new review");
		ReviewItem reviewItem = new ReviewItem(review);
		reviewItem.addHistory(review.getUserId(), HistoryType.create, "Creating review.");
		return reviewDAO.createReview(reviewItem);
	}

	@Override
	public Boolean deleteReview(Integer reviewId) {
		logger.debug("Deleting a review");
		ReviewItem reviewItem = reviewDAO.getReviewById(reviewId);
		if (reviewItem != null) {
			reviewDAO.deleteReview(reviewItem);
			return true;
		}
		return false;
	}

	@Override
	public List<ReviewItem> fetchReviews(FilterReviews filters) {
		logger.debug("Fetching a list of reviews");
		return reviewDAO.getReviews(filters);
	};

	@Override
	public ReviewItem updateReview(Integer reviewId, ReviewForm review) {
		logger.debug("Fetching a list of reviews");
		ReviewItem reviewItem = reviewDAO.getReviewById(reviewId);
		if (reviewItem == null) {
			return null;
		}
		reviewItem.update(review);
		// Only update on 'submit' action
		if (review.getAction().equals(ReviewAction.submit)) {
			reviewItem.addHistory(review.getUserId(), HistoryType.update, "Updating review.");
			reviewDAO.updateReview(reviewItem);
		}
		return reviewItem;
	};

	@Override
	public ReviewItem updateReview(Integer reviewId, ReviewStatus status) {
		logger.debug("Fetching a list of reviews");
		ReviewItem reviewItem = reviewDAO.getReviewById(reviewId);
		if (reviewItem != null) {
			reviewItem.setStatus(status);
			reviewItem.addHistory("admin", HistoryType.changeStatus, "Changing status of review to " + status);
			reviewDAO.updateReview(reviewItem);
			return reviewItem;
		}
		return null;
	};

}