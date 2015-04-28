package org.beesden.ratings.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.beesden.ratings.utils.constants.HistoryType;
import org.beesden.ratings.utils.constants.TableNames;

@Entity
@Table(name = TableNames.HISTORY)
public class ReviewHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Date date;

	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@Column
	private String message;

	@Column
	private HistoryType type;

	@Column
	private String user;

	ReviewHistory() {
	}

	ReviewHistory(String user, HistoryType type, String message) {
		this.date = new Date();
		this.type = type;
		this.user = user;
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public Integer getId() {
		return id;
	}

	public HistoryType getType() {
		return type;
	}

	public String getUser() {
		return user;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setType(HistoryType type) {
		this.type = type;
	}

	public void setUser(String user) {
		this.user = user;
	}

}