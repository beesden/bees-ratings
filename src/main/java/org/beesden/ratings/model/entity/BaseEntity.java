package org.beesden.ratings.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	protected Date lastEdited;

	@Column
	protected String lastEditedBy;

	@Column
	protected Date submitted;

	@Column
	protected String submittedBy;

	public Date getLastEdited() {
		return lastEdited;
	}

	public String getLastEditedBy() {
		return lastEditedBy;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	public void setLastEditedBy(String lastEditedBy) {
		this.lastEditedBy = lastEditedBy;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
}
