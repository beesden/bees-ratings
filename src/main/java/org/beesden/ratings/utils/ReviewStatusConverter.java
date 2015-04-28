package org.beesden.ratings.utils;

import java.beans.PropertyEditorSupport;

import org.beesden.ratings.utils.constants.ReviewStatus;

public class ReviewStatusConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		text = text.toLowerCase();
		ReviewStatus status = ReviewStatus.valueOf(text);
		setValue(status);
	}
	
}