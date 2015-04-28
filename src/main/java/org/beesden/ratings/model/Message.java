package org.beesden.ratings.model;

public class Message {

	private Object[] arguments;

	private String key;

	private String message;

	public Message(String message, String key, Object... arguments) {
		this.key = key;
		this.arguments = arguments;
		this.message = message;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public String getKey() {
		return key;
	}

	public String getMessage() {
		return message;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
