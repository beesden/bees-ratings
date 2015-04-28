package org.beesden.ratings.model;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

	private List<Message> messages = new ArrayList<>();

	public void addMessage(String defaultMessage, String key, Object[] arguments) {
		Message message = new Message(defaultMessage, key.toLowerCase(), arguments);
		messages.add(message);

	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
