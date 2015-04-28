package org.beesden.ratings.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.beesden.ratings.model.ReviewForm;
import org.beesden.ratings.utils.constants.AppConfig;
import org.beesden.ratings.utils.constants.HistoryType;
import org.beesden.ratings.utils.constants.ReviewStatus;
import org.beesden.ratings.utils.constants.TableNames;
import org.hibernate.annotations.OrderBy;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = TableNames.REVIEW)
public class ReviewItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Column
	private String author;

	@Lob
	private String content;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "reviewId", nullable = false)
	@OrderBy(clause = "date")
	@JsonIgnore
	private List<ReviewHistory> history = new ArrayList<>();

	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@Column
	private String productId;

	@NotNull
	@Max(AppConfig.MAX_RATING)
	@Column
	private Float rating;

	@Column
	private ReviewStatus status;

	@Column
	private String title;

	public ReviewItem() {
		this.status = ReviewStatus.unpublished;
		super.submitted = new Date();
	}

	public ReviewItem(ReviewForm review) {
		this();
		update(review);
		super.submittedBy = review.getUserId();
	}

	public void addHistory(String userId, HistoryType historyType, String message) {
		ReviewHistory historyItem = new ReviewHistory(userId, historyType, message);
		history.add(historyItem);
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public List<ReviewHistory> getHistory() {
		return history;
	}

	public Integer getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public Float getRating() {
		return rating;
	}

	public ReviewStatus getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHistory(List<ReviewHistory> history) {
		this.history = history;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public void setStatus(ReviewStatus status) {
		this.status = status;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void update(ReviewForm review) {
		// Update the review item
		this.author = review.getUserId();
		this.content = review.getContent();
		this.rating = review.getRating();
		this.title = review.getTitle();
		this.productId = review.getProductId();
		// Update the timestamps
		super.lastEdited = new Date();
		super.lastEditedBy = review.getUserId();
	}

}