package org.beesden.ratings.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.beesden.ratings.model.FilterReviews;
import org.beesden.ratings.model.ReviewForm;
import org.beesden.ratings.model.entity.ReviewItem;
import org.beesden.ratings.service.ReviewService;
import org.beesden.ratings.utils.ReviewStatusConverter;
import org.beesden.ratings.utils.constants.ReviewAction;
import org.beesden.ratings.utils.constants.ReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	static final Logger logger = Logger.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	/**
	 * Create a new review
	 *
	 * @param filters
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ReviewItem> createReview(@Valid @RequestBody ReviewForm review) {
		logger.info("Creating a new review");

		ReviewItem reviewItem = reviewService.createReview(review);
		HttpStatus httpStatus = review != null ? HttpStatus.CREATED : HttpStatus.NOT_MODIFIED;
		return new ResponseEntity<ReviewItem>(reviewItem, httpStatus);
	}

	/**
	 * Delete a review
	 *
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/{reviewId}", method = RequestMethod.DELETE)
	public ResponseEntity<ReviewItem> deleteReview(@PathVariable Integer reviewId) {
		logger.info("Deleting a review with id " + reviewId);

		Boolean success = reviewService.deleteReview(reviewId);
		HttpStatus httpStatus = success ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<ReviewItem>(new ReviewItem(), httpStatus);
	};

	/**
	 * Fetch a list of reviews
	 *
	 * @param filters
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ReviewItem>> getReviews(@Valid @ModelAttribute FilterReviews filters) {
		logger.info("Fetching a list of reviews");

		List<ReviewItem> reviewItems = reviewService.fetchReviews(filters);
		HttpStatus httpStatus = reviewItems.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<List<ReviewItem>>(reviewItems, httpStatus);
	};

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(ReviewStatus.class, new ReviewStatusConverter());
	};

	/**
	 * Change the status of a review
	 *
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/{reviewId}", method = RequestMethod.PUT)
	public ResponseEntity<ReviewItem> updateReview(@PathVariable Integer reviewId, @Valid @RequestBody ReviewForm reviewForm) {
		logger.info("Updating a review with id " + reviewId);

		ReviewItem reviewItem = reviewService.updateReview(reviewId, reviewForm);
		HttpStatus httpStatus = reviewItem != null ? reviewForm.getAction().equals(ReviewAction.submit) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED : HttpStatus.NOT_FOUND;
		return new ResponseEntity<ReviewItem>(reviewItem, httpStatus);
	}

	/**
	 * Update a review
	 *
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/{reviewId}", method = RequestMethod.PATCH)
	public ResponseEntity<ReviewItem> updateReview(@PathVariable Integer reviewId, @Valid @RequestParam ReviewStatus status) {
		logger.info("Change the status a review with id " + reviewId + " to " + status);

		ReviewItem reviewItem = reviewService.updateReview(reviewId, status);
		HttpStatus httpStatus = reviewItem != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<ReviewItem>(reviewItem, httpStatus);
	}
}