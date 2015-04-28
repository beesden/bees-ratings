package org.beesden.ratings.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.beesden.ratings.utils.constants.AppConfig;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ReviewForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String action;

	private String content;

	@NotBlank
	private String displayName;

	@NotBlank
	private String productId;

	@NotNull
	@Max(AppConfig.MAX_RATING)
	private Float Rating;

	@Length(max = 255)
	private String title;

	@NotBlank
	private String userId;

	public String getAction() {
		return action;
	}

	public String getContent() {
		return content;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getProductId() {
		return productId;
	}

	public Float getRating() {
		return Rating;
	}

	public String getTitle() {
		return title;
	}

	public String getUserId() {
		return userId;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setRating(Float rating) {
		Rating = rating;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}