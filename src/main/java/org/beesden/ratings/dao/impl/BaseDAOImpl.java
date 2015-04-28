package org.beesden.ratings.dao.impl;

import org.beesden.ratings.model.FilterResults;
import org.beesden.ratings.utils.constants.AppConfig;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAOImpl<T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Criteria createFilterCriteria(Criteria criteria, FilterResults filters) {
		// Add results limit
		Integer limit = AppConfig.DEFAULT_RESULTS;
		if (filters.getLimit() != null) {
			limit = filters.getLimit();
		}
		// Add offset
		if (filters.getPage() != null) {
			Integer offset = limit * (filters.getPage() - 1);
			criteria.setFirstResult(offset);
		}
		criteria.setMaxResults(limit);

		return criteria;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}