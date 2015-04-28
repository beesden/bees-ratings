package org.beesden.ratings.model;

import javax.validation.constraints.Min;

import org.beesden.ratings.utils.constants.SortField;
import org.beesden.ratings.utils.constants.SortOrder;
import org.hibernate.validator.constraints.Range;

public abstract class FilterResults {

	@Range(min = 1, max = 100)
	private Integer limit;

	@Min(0)
	private Integer page;

	private SortField sortField;

	private SortOrder sortOrder;

	public Integer getLimit() {
		return limit;
	}

	public Integer getPage() {
		return page;
	}

	public SortField getSortField() {
		return sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSortField(SortField sortField) {
		this.sortField = sortField;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
