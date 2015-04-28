package org.beesden.ratings.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.beesden.ratings.utils.constants.AppConfig;

public class FilterReviews extends FilterResults {

	private String author;

	@Max(value = AppConfig.MAX_RATING)
	private Integer maxRating;

	@Min(0)
	private Integer minRating;

	private String productId;

	public String getAuthor() {
		return author;
	}

	public Integer getMaxRating() {
		return maxRating;
	}

	public Integer getMinRating() {
		return minRating;
	}

	public String getProductId() {
		return productId;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setMaxRating(Integer maxRating) {
		this.maxRating = maxRating;
	}

	public void setMinRating(Integer minRating) {
		this.minRating = minRating;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
