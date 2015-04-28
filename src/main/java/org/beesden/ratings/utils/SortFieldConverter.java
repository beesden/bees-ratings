package org.beesden.ratings.utils;

import java.beans.PropertyEditorSupport;

import org.beesden.ratings.utils.constants.SortField;

public class SortFieldConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		text = text.toLowerCase();
		SortField field = SortField.valueOf(text);
		setValue(field);
	}
}