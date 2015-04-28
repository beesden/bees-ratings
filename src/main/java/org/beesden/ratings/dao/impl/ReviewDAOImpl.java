package org.beesden.ratings.dao.impl;

import java.util.List;

import org.beesden.ratings.dao.ReviewDAO;
import org.beesden.ratings.model.FilterReviews;
import org.beesden.ratings.model.entity.ReviewItem;
import org.beesden.ratings.utils.constants.SortField;
import org.beesden.ratings.utils.constants.SortOrder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ReviewDAOImpl extends BaseDAOImpl<ReviewItem> implements ReviewDAO {

	/**
	 * Add search criteria onto the object
	 *
	 * @param criteria
	 * @param filters
	 * @return
	 */
	private Criteria createFilterCriteria(Criteria criteria, FilterReviews filters) {
		// Get default criteria
		criteria = super.createFilterCriteria(criteria, filters);
		// Filter by Product ID
		if (filters.getProductId() != null) {
			criteria.add(Restrictions.eq("productId", filters.getProductId()));
		}
		// Filter by Author
		if (filters.getAuthor() != null) {
			criteria.add(Restrictions.eq("author", filters.getAuthor()));
		}
		// Filter by Min Rating
		if (filters.getMinRating() != null) {
			criteria.add(Restrictions.ge("rating", filters.getMinRating()));
		}
		// Add sort order
		SortField sortField = filters.getSortField();
		if (sortField != null) {
			if (filters.getSortOrder().equals(SortOrder.asc)) {
				criteria.addOrder(Order.asc(sortField.name()));
			} else {
				criteria.addOrder(Order.desc(sortField.name()));
			}
		}
		return criteria;
	}

	@Override
	public ReviewItem createReview(ReviewItem review) {
		Integer id = (Integer) getSession().save(review);
		review.setId(id);
		return review;
	}

	@Override
	public void deleteReview(ReviewItem reviewItem) {
		getSession().delete(reviewItem);
	}

	@Override
	public ReviewItem getReviewById(Integer reviewId) {
		return (ReviewItem) getSession().get(ReviewItem.class, reviewId);
	}

	@Override
	public List<ReviewItem> getReviews(FilterReviews filters) {
		Criteria criteria = getSession().createCriteria(ReviewItem.class);
		return createFilterCriteria(criteria, filters).list();
	}

	@Override
	public void updateReview(ReviewItem review) {
		getSession().update(review);
	}

}